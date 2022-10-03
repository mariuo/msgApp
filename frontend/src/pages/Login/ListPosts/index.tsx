import PostCard from 'components/PostCard';
import PostCreateCard from 'components/PostCreateCard';
import { useEffect, useState } from 'react';
import { PostType } from 'types/postType';
import { BASE_URL } from 'util/request';
import { getAuthData } from 'util/storage';
import './styles.css';

const ListsPosts = () => {
    const [userActiveId, setUserActiveId] = useState(0);
    const [listPost, setListPost] = useState<PostType[]>([]);

    useEffect(() => {
        let url = BASE_URL + "/post/stream";
        const sse = new EventSource(url);
        sse.addEventListener("sse-post", (event) => {
            const data = JSON.parse(event.data);
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

    // const [listNotify, setListNotify] = useState<Notification[]>([]);
    // useEffect(() => {
    //     let url = BASE_URL + "/notification/stream/" + getAuthData().userId;
    //     const sse2 = new EventSource(url);
    //     sse2.addEventListener("sse-notify", (event) => {
    //         // console.log(event.data);
    //         const data = JSON.parse(event.data);
    //         // console.log(data);
    //         setListNotify(data);
    //         // console.log(listNotify);
    //         // const numberId = getAuthData().userId
    //         // setUserActiveId(numberId);
    //     });

    //     sse2.onerror = () => {
    //         sse2.close();
    //     };
    //     return () => {
    //         sse2.close();
    //     };
    // }, [setListNotify]);
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
            {/* <div className='navbar notification'>
                <span>Notifications {listNotify?.length}</span>
            </div> */}
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
                        ''//<h1 style={{ marginTop: 50 }}>Loading...</h1>
                    )}

                </div >
            </div>
        </div>
    );
}

export default ListsPosts;