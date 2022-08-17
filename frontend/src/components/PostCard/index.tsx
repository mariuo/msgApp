import "./styles.css";
import { BiAlignMiddle } from 'react-icons/bi';
import { AiFillLike } from 'react-icons/ai';
import { GoComment } from 'react-icons/go';
import { PostType } from "types/postType";
import PostComment from "components/PostComment";
import PostCommentCreate from "components/PostCommentCreate";
import { FaTrashAlt } from "react-icons/fa";
import { AxiosRequestConfig } from "axios";
import { BASE_URL, requestBackend } from "util/request";

type Props = {
    postType: PostType;
    userActiveId: number;
}
const PostCard = ({ postType, userActiveId }: Props) => {
    const { comments } = postType;
    const idPost = postType.id;
    const handleDeletePost = () => {
        if (!window.confirm("Are you sure?")) {
            return;
        }
        const params: AxiosRequestConfig = {
            method: 'DELETE',
            url: `/post/${idPost}`,
            baseURL: BASE_URL,
            withCredentials: true,
        }

        requestBackend(params)
            .then(response => {
                window.location.reload();
            })
    };

    return (
        <div className='post-card base-card'>
            <div className="post-card-top">
                <div className="post-contact">
                    <img src={postType?.author.imageUrlProfile} alt={postType?.author.name} />
                    <h6>{postType?.author.name}</h6>
                </div>
                {postType.author.id === userActiveId && (
                    <div className="post-comment-icon" onClick={handleDeletePost}>
                        <FaTrashAlt />
                    </div>

                )}
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
                {comments.map((comment) => (
                    <PostComment key={comment.id} comment={comment} post={postType} />
                ))}
                <PostCommentCreate post={postType} userActiveId={userActiveId} />
            </div>
        </div >
    );
}
export default PostCard;