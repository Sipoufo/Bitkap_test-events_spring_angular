export interface CommentResponse {
    id: number;
    value: string;
    eventId: number;
    eventTitle: string;
    createByUserId: number;
    createByUserName: string;
    createAt: Date;
}