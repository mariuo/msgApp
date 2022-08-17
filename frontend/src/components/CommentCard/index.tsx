import { Comment } from "types/comment";
import "./styles.css";
import { FaTrash } from "react-icons/fa";
import { AxiosRequestConfig } from "axios";
import { BASE_URL, requestBackend } from "util/request";
import { useContext } from "react";
import { AuthContext } from "AuthContext";
import { PostType } from "types/postType";

type Props = {
    comment: Comment;
    post: PostType;
}

const CommentCard = ({ comment, post }: Props) => {
    const data = { idPost: post.id, comment: comment }

    const { authContextData } = useContext(AuthContext);

    //Precisa corrigir quando deleta, dar um refresh, e qnd tenta colocar outro comment depois do primeiro, ele não faz.

    const handleDeleteComment = () => {
        const params: AxiosRequestConfig = {
            method: 'DELETE',
            url: "/post/comment/",
            baseURL: BASE_URL,
            withCredentials: true,
            data: data
        }

        requestBackend(params)
            .then(response => {
                window.location.reload();
            })
    };

    return (
        <div className="post-comment-content">
            <div className="post-comment-title">
                <img src={comment.authorComment.imageUrlProfile} alt="{comment.authorComment.name}" />
                <h5>{comment.authorComment.name}<b>:</b></h5>
                <span>{comment.content}</span>
            </div>
            {comment.authorComment.name === authContextData.tokenData?.user_name && (
                <div className="post-comment-icon" onClick={handleDeleteComment}>
                    <FaTrash />
                </div>

            )}
        </div>
    )
}

export default CommentCard;