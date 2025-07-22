export class DateUtil {

  static getIsoString(date: Date): string|null {
    if (!date) {
      return null;
    }
    const now = new Date(date);
    const isoNow = now.toISOString();
    console.log(now);
    return isoNow;
  }

  static getDateFromIsoString(isoDate: string): Date|null {
    if (isoDate == null || isoDate === '') {
      return null;
    }
    return new Date(isoDate);
  }

}
