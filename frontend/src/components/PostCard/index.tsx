import "./styles.css";
import { BiAlignMiddle } from 'react-icons/bi';
import { AiFillLike } from 'react-icons/ai';
import { GoComment } from 'react-icons/go'

const PostCard = () => {
    return (
        <div className='post-card base-card'>
            <div className="post-card-top">
                <div className="post-contact">
                    <img alt="https://avatars.githubusercontent.com/u/30843415?v=4" src="https://avatars.githubusercontent.com/u/30843415?v=4" />
                    <h6>mario</h6>
                </div>
                <BiAlignMiddle />
            </div>
            <div className="post-card-mid">
                <p>Hello everyone!!! I hope everythings is fine.</p>
            </div>
            <div>
                <hr />
            </div>
            <div className="post-card-bottom">
                <div className="post-card-like">
                    <AiFillLike color="blue" />
                    <span>2</span>
                </div>
                <div className="post-card-comment">
                    <GoComment />
                    <span>1</span>
                </div>
            </div>
        </div >
    );
}
export default PostCard;