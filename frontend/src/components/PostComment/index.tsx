import { Comment } from "types/comment";
import "./styles.css";

type Props = {
    comment: Comment;
}

const PostComment = ({ comment }: Props) => {

    return (
        <div className="post-comment-content">
            <div className="post-comment-title">
                <img src={comment.authorComment.imageUrlProfile}></img>
                <h5>{comment.authorComment.name}<b>:</b></h5>
                <span>{comment.content}</span>
            </div>
        </div>
    )
}

export default PostComment;