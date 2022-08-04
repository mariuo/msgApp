import PostCard from 'components/PostCard';

import "./styles.css";

const PostList = () => {
    return (
        <div className='post-list-container'>
            <PostCard />
            <PostCard />
            <PostCard />
            <PostCard />
            <PostCard />
        </div >
    );
}
export default PostList;