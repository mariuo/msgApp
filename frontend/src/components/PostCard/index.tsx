import "./styles.css";
import { BiAlignMiddle } from 'react-icons/bi';
import { AiFillLike } from 'react-icons/ai';
import { GoComment } from 'react-icons/go';
import { PostType } from "types/postType";

type Props = {
    postType: PostType;
}
const PostCard = ({ postType }: Props) => {
    return (
        <div className='post-card base-card'>
            <div className="post-card-top">
                <div className="post-contact">
                    <img src={postType?.author.imageUrlProfile} alt={postType?.author.userName} />
                    <h6>{postType?.author.userName}</h6>
                </div>
                <BiAlignMiddle />
            </div>
            <div className="post-card-mid">
                <p>{postType?.content}</p>
            </div>
            <div>
                <hr />
            </div>
            <div className="post-card-bottom">
                <div className="post-card-like">
                    <AiFillLike color="blue" />
                    <span>{postType?.likes.length}</span>
                </div>
                <div className="post-card-comment">
                    <GoComment />
                    <span>{postType?.comments.length}</span>
                </div>
            </div>
        </div >
    );
}
export default PostCard;