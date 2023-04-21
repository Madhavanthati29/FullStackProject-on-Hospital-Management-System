import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-paging',
  templateUrl: './paging.component.html',
  styleUrls: ['./paging.component.css']
})
export class PagingComponent implements OnInit, OnChanges {

  @Input() totalItem: number = 0;
  @Input() pageSize: number = 0;
  @Input() offset: number = 0;
  @Output() onNextPageClick: EventEmitter<any> = new EventEmitter();
  @Output() onPreviousPageClick: EventEmitter<any> = new EventEmitter();
  @Output() onFirstPageClick: EventEmitter<any> = new EventEmitter();
  @Output() onLastPageClick: EventEmitter<any> = new EventEmitter();
  currentPage = 1;
  isNextPageDisabled: boolean = true;
  isPreviousPageDisabled: boolean = true;
  isFirstPageDisabled: boolean = true;
  isLastPageDisabled: boolean = true;
  totalPages = 1;
  firstPage = 1;

  constructor() {
  }

  changeTotalPage(): void {
    this.totalPages = Math.ceil(this.totalItem / this.pageSize);
    if (this.totalItem === 1) {
      this.currentPage = 1;
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.changeTotalPage();
    this.updateButtonState();
  }

  ngOnInit(): void {
    this.changeTotalPage();
    this.updateButtonState();
  }

  onNextPage(): void {
    this.currentPage += 1;
    this.isPreviousPageDisabled = false;
    this.isFirstPageDisabled = false;
    this.updateButtonState();
    this.onNextPageClick.emit(this.currentPage);
  }

  onPreviousPage(): void {
    if (this.currentPage !== 1) {
      this.currentPage -= 1;
      this.isNextPageDisabled = false;
      this.isLastPageDisabled = false;
    }
    this.updateButtonState();
    this.onPreviousPageClick.emit(this.currentPage);
  }

  onLastPage(): void {
    this.currentPage = this.totalPages;
    this.updateButtonState();
    this.onLastPageClick.emit(this.currentPage);
  }

  onFirstPage(): void {
    this.currentPage = this.firstPage;
    this.updateButtonState();
    this.onFirstPageClick.emit(this.currentPage);
  }

  updateButtonState(): void {
    if (this.currentPage >= this.totalPages) {
      this.isNextPageDisabled = true;
      this.isPreviousPageDisabled = false;
      this.isFirstPageDisabled = false;
      this.isLastPageDisabled = true;
    }
    if (this.currentPage === 1) {
      this.isPreviousPageDisabled = true;
      this.isNextPageDisabled = false;
      this.isFirstPageDisabled = true;
      this.isLastPageDisabled = false;
    }
    if (this.currentPage === 1 && this.totalItem < this.pageSize) {
      this.isPreviousPageDisabled = true;
      this.isNextPageDisabled = true;
      this.isFirstPageDisabled = true;
      this.isLastPageDisabled = true;
    }
    this.offset = this.currentPage;
  }

}
