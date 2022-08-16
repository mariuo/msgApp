import "./styles.css";
import { BiAlignMiddle } from 'react-icons/bi';
import { AiFillLike } from 'react-icons/ai';
import { GoComment } from 'react-icons/go';
import { PostType } from "types/postType";
import PostComment from "components/PostComment";
import { Comment } from "types/comment";

type Props = {
    postType: PostType;
}
const PostCard = ({ postType }: Props) => {
    const { comments } = postType;

    return (
        <div className='post-card base-card'>
            <div className="post-card-top">
                <div className="post-contact">
                    <img src={postType?.author.imageUrlProfile} alt={postType?.author.name} />
                    <h6>{postType?.author.name}</h6>
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
            <div className="post-comment-container">
                {comments.map((x) => (
                    <PostComment key={x.id} comment={x} />

                ))}
            </div>
        </div >
    );
}
export default PostCard;