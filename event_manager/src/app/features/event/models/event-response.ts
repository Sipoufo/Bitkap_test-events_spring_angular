export interface EventResponse {
    id: number,
    title: string,
    description: string,
    eventDate: Date,
    organizerId: number,
    organizerName: string,
    commentSize: number,
    createByUserId: number,
    createByUserName: string,
    createAt: Date,
    enabled: boolean,
}