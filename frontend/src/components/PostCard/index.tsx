import "./styles.css";

import { BsSuitHeart, BsSuitHeartFill } from "react-icons/bs";
import { GoComment } from 'react-icons/go';
import { PostType } from "types/postType";
import { FaTrashAlt } from "react-icons/fa";
import { AxiosRequestConfig } from "axios";
import { BASE_URL, requestBackend } from "util/request";
import CommentCard from "components/CommentCard";
import CommentCreateCard from "components/CommentCreateCard";

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
    const deleteLike = () => {

        const formLike = {
            idPost: postType.id,
            user: {
                id: userActiveId
            }
        }

        const params: AxiosRequestConfig = {
            method: 'DELETE',
            url: "/post/like",
            baseURL: BASE_URL,
            withCredentials: true,
            data: formLike
        }

        requestBackend(params)
            .then(response => {
                window.location.reload();

            }).catch((e) => {
                console.log("error" + e)
            })

    }
    const insertLike = () => {

        //const commResu = formData.comment;

        //console.log(commResu)

        const formLike = {
            idPost: postType.id,
            user: {
                id: userActiveId
            }
        }

        const params: AxiosRequestConfig = {
            method: 'POST',
            url: "/post/like",
            baseURL: BASE_URL,
            withCredentials: true,
            data: formLike
        }

        requestBackend(params)
            .then(response => {
                window.location.reload();

            }).catch((e) => {
                console.log("error" + e)
            })

    }

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

                    {/* <AiFillLike color="blue" /> */}
                    {postType?.likes.find((x) => x.id === userActiveId) !== undefined ? (
                        <BsSuitHeartFill
                            onClick={deleteLike}
                            className="post-like-icon"
                            size={15}
                            color="red"
                        />
                    ) : (
                        <BsSuitHeart onClick={insertLike} className="post-like-icon" size={15} />
                    )}

                    <span>{postType?.likes.length}</span>
                </div>
                <div className="post-card-comment">
                    <GoComment />
                    <span>{postType?.comments.length}</span>
                </div>
            </div>
            <div className="post-comment-container">
                {comments.map((comment) => (
                    <CommentCard key={comment.id} comment={comment} post={postType} />
                ))}
                <CommentCreateCard post={postType} userActiveId={userActiveId} />
            </div>
        </div >
    );
}
export default PostCard;