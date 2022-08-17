import { Comment } from "./comment";

export type CommentRequest = {
    idPost: number;
    comment: Comment;

}