export class CategoryUpdate{

  categoryName: string;
  categoryOldName: string;


  constructor(categoryName: string, categoryOldName: string) {
    this.categoryName = categoryName;
    this.categoryOldName = categoryOldName;
  }
}
