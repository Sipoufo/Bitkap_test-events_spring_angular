export interface EventResponse {
    id: number,
    title: string,
    description: string,
    eventDate: Date,
    organizerId: number,
    organizerName: string,
    commentSize: number,
    createByUserId: string,
    createByUserName: string,
    createAt: Date,
    enabled: boolean,
}