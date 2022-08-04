import { Comment } from "./comment";
import { User } from "./user";

export type PostType = {
    id: number;
    content: string;
    created: string;
    imageUrl: string;
    author: User;
    comments: Comment[];
    likes: User[];
};