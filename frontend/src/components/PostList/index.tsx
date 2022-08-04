import "./styles.css";
import PostCard from 'components/PostCard';
import { PostType } from "types/postType";
import axios from "axios";
import { BASE_URL } from "util/request";
import { useState, useEffect } from "react";


const PostList = () => {

    const [listPost, setListPost] = useState<PostType[]>([]);

    useEffect(() => {
        axios.get(BASE_URL + "/post")
            .then(response => {
                setListPost(response.data);
                //console.log(response.data);
            })
    }, []);

    return (
        <div className='post-list-container'>
            {listPost.map(post => {
                return (
                    <PostCard key={post.id} postType={post} />
                )
            })}

        </div >
    );
}
export default PostList;