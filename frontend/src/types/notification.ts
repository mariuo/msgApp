import { NotificationType } from "./notificationType";
import { User } from "./user";

export type Notification = {
    id: number;
    content: string;
    delivered: boolean;
    readed: boolean;
    notificationType: NotificationType;
    userToDTO: User;
    userFromDTO: User;
};