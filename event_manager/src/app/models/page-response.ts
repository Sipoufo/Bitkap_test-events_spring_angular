export interface PageResponse<T> {
  items?: Array<T>;
  currentPageNumber?: number;
  pageSize?: number;
  numberOfElements?: number;
  totalElements?: number;
  totalPages?: number;
}
