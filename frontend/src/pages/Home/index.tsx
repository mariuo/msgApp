import PostCreate from 'components/PostCreate';
import PostList from 'components/PostList';
import './styles.css';

const Home = () => {
    return (
        <div className='home-container'>
            <PostCreate />
            <div className='list-container'>
                <PostList />
            </div>

        </div>
    );
}

export default Home;