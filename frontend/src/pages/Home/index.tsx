import PostList from 'components/PostList';
import './styles.css';

const Home = () => {
    return (
        <div className='home-container'>
            <div className='create-post-container base-card'>
                <form action="">
                    <input
                        type="text"
                        placeholder="Type something..."
                        name="postContent"
                        className='form-control input-base mb-4'
                    />
                </form>
                <div className=''>
                    <button className='btn btn-primary button-post'>Post</button>
                </div>
            </div>
            <div className='list-container'>
                <PostList />
            </div>

        </div>
    );
}

export default Home;