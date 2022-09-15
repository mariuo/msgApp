
import { AxiosRequestConfig } from 'axios';
import PostCard from 'components/PostCard';
import PostCreateCard from 'components/PostCreateCard';
import { useEffect, useState } from 'react';
import { PostType } from 'types/postType';
import { BASE_URL, requestBackend } from 'util/request';
import { getAuthData } from 'util/storage';
import './styles.css';

const ListsPosts = () => {
    const [listPost, setListPost] = useState<PostType[]>([]);
    // const [listPost, setListPost] = useState<PostType[]>([]);
    const [userActiveId, setUserActiveId] = useState(0);

    useEffect(() => {
        // let url = BASE_URL + "/post/stream-flux";
        // let url = BASE_URL + "/post/stream";
        // const sse = new EventSource(url);
        // post-list-event
        // sse.onmessage = (e) => {
        //     console.log(e.data);
        // };

        let url = BASE_URL + "/post/stream";
        const sse = new EventSource(url);
        sse.addEventListener("post-list-event", (event) => {
            const data = JSON.parse(event.data);
            // console.log(event.data);
            // console.log(data);
            setListPost(data);
            const numberId = getAuthData().userId
            setUserActiveId(numberId);
        });

        sse.onerror = () => {
            sse.close();
        };
        return () => {
            sse.close();
        };
    }, [setListPost, userActiveId]);

    // useEffect(() => {
    //     const numberId = getAuthData().userId
    //     setUserActiveId(numberId);
    //     const params: AxiosRequestConfig = {
    //         method: 'GET',
    //         url: "/post",
    //         baseURL: BASE_URL,
    //         withCredentials: true
    //     }

    //     requestBackend(params)
    //         .then(response => {
    //             // console.log(userActiveId);
    //             setListPost(response.data);
    //             //console.log(response.data);
    //         })
    // }, [setListPost, userActiveId]);
    return (
        <div className='home-container'>

            <PostCreateCard userActiveId={userActiveId} />

            <div className='list-container'>
                <div className='post-list-container'>
                    {listPost.length > 0 ? (
                        listPost
                            .sort((a, b) => Date.parse(b.created) - Date.parse(a.created))
                            .map((post, index) => (
                                <PostCard key={post.id} postType={post} userActiveId={userActiveId} />
                            ))

                    ) : (
                        <h1 style={{ marginTop: 50 }}>Loading...</h1>
                    )}

                </div >
            </div>
        </div>
    );
}

export default ListsPosts;