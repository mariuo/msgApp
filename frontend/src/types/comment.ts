import { User } from "./user";

export type Comment = {
    id: number;
    content: string;
    authorComment: User;
};