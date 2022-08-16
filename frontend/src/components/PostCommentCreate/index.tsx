import "./styles.css"
import { IoIosSend } from "react-icons/io";


const PostCommentCreate = () => {
    return (
        <div className="post-comment-create-container">
            <form name="comment-form">
                <input className="create-comment-input" type="text" placeholder="comment..." />
                <button className="post-comment-create-button" type="submit">
                    <IoIosSend />
                </button>
            </form>
        </div>
    )
}

export default PostCommentCreate;