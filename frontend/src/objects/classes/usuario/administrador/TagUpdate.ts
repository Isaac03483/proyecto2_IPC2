export class TagUpdate{

  tagName: string;
  oldTagName: string;

  constructor(tagName: string, oldTagName: string) {
    this.tagName = tagName;
    this.oldTagName = oldTagName;
  }
}
