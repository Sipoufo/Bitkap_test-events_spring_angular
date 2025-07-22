export interface SimpleResponse<T> {
  items?: T;
  operationStatus?: string;
  operationMessage?: string;
}
