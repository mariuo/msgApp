import "./styles.css"
import { IoIosSend } from "react-icons/io";
import { BASE_URL, requestBackend } from "util/request";
import { AxiosRequestConfig } from "axios";
import { useState } from "react";
import { PostType } from "types/postType";
import history from "util/history";

type Props = {
    post: PostType;
    userActiveId: number;
}

const PostCommentCreate = ({ post, userActiveId }: Props) => {

    //const { handleSubmit } = useForm<CommentRequest>();
    const [newComment, setNewComment] = useState("");


    const onSubmitComment = () => {

        if (newComment !== "" && newComment !== undefined) {

            const formComment = {
                idPost: post.id,
                comment: {
                    id: 0,
                    content: newComment,
                    authorComment: {
                        id: userActiveId
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
                    setNewComment("");
                    history.goBack();

                }).catch((e) => {
                    console.log("error" + e)
                })
        }
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