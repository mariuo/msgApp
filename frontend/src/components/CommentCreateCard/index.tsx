import "./styles.css"
import { IoIosSend } from "react-icons/io";
import { BASE_URL, requestBackend } from "util/request";
import { AxiosRequestConfig } from "axios";
import { PostType } from "types/postType";
import { useForm } from "react-hook-form";

type Props = {
    post: PostType;
    userActiveId: number;
}
type FormData = {
    comment: string;
}
const CommentCreateCard = ({ post, userActiveId }: Props) => {

    const { register, handleSubmit, formState: { errors }, } = useForm<FormData>();

    const onSubmitComment = (formData: FormData) => {

        const commResu = formData.comment;

        //console.log(commResu)

        const formComment = {
            idPost: post.id,
            comment: {
                id: 0,
                content: commResu,
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
                window.location.reload();

            }).catch((e) => {
                console.log("error" + e)
            })

    }

    return (
        <div className="post-comment-create-container">
            <form className="comment-form" onSubmit={handleSubmit(onSubmitComment)}>
                <input
                    {...register('comment', {
                        required: 'Required field.'
                    })}
                    className={`form-control ${errors.comment ? 'is-invalid' : ''}`}
                    // className="create-comment-input"
                    type="text"
                    placeholder="comment..."
                // value={newComment}
                // onChange={(e) => setNewComment(e.target.value)}
                />
                <button className="post-comment-create-button" type="submit">
                    <IoIosSend />
                </button>
            </form>
        </div>
    )
}

export default CommentCreateCard;