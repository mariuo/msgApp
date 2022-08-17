import { Comment } from "types/comment";
import "./styles.css";
import { FaTrash } from "react-icons/fa";
import { User } from "types/user";
import { AxiosRequestConfig } from "axios";
import { BASE_URL, requestBackend } from "util/request";
import { useContext } from "react";
import { AuthContext } from "AuthContext";

type Props = {
    comment: Comment;
    user: User;
    id: number;
}

const PostComment = ({ comment, user, id }: Props) => {
    const data = { idPost: id, comment: comment }

    const { authContextData } = useContext(AuthContext);

    //Precisa corrigir quando deleta, dar um refresh, e qnd tenta colocar outro comment depois do primeiro, ele não faz.

    const handleDeleteComment = () => {
        //console.log(data)
        const params: AxiosRequestConfig = {
            method: 'DELETE',
            url: "/post/comment/",
            baseURL: BASE_URL,
            withCredentials: true,
            data: data
        }

        requestBackend(params)
            .then(response => {
                //console.log(response.data);
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

export default PostComment;