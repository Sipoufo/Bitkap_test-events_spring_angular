export interface CommentResponse {
    id: number;
    value: string;
    eventId: number;
    eventTitle: string;
    createByUserId: string;
    createByUserName: string;
    createAt: Date;
}