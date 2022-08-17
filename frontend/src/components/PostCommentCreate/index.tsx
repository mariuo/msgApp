import "./styles.css"
import { IoIosSend } from "react-icons/io";
import { BASE_URL, requestBackend } from "util/request";
import { AxiosRequestConfig } from "axios";
import { useContext, useState } from "react";
import { PostType } from "types/postType";
import { AuthContext } from "AuthContext";

type Props = {
    post: PostType;
}

const PostCommentCreate = ({ post }: Props) => {
    const { authContextData } = useContext(AuthContext);

    //const { handleSubmit } = useForm<CommentRequest>();
    const [newComment, setNewComment] = useState("");

    const onSubmitComment = () => {
        const formComment = {
            idPost: post.id,
            comment: {
                id: null,
                content: newComment,
                authorComment: {
                    id: authContextData.userId
                }
            }
        }

        const params: AxiosRequestConfig = {
            method: 'POST',
            url: "/post/comment/",
            baseURL: BASE_URL,
            withCredentials: true,
            data: formComment
        }

        requestBackend(params)
            .then(response => {
                console.log(response.data);
            }).catch((e) => {
                console.log("error" + e)
            })
    }

    return (
        <div className="post-comment-create-container">
            <form onSubmit={onSubmitComment}>
                <input
                    required
                    className="create-comment-input"
                    type="text"
                    placeholder="comment..."
                    value={newComment}
                    onChange={(e) => setNewComment(e.target.value)}
                />
                <button className="post-comment-create-button" type="submit">
                    <IoIosSend />
                </button>
            </form>
        </div>
    )
}

export default PostCommentCreate;