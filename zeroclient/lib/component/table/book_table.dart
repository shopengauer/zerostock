import 'package:zeroclient/component/editablefield/editable_field.dart';
import 'package:zeroclient/directives/highlight.dart';
import 'package:zeroclient/domain/booky.dart';
import "package:angular/angular.dart";

@Component(
    selector: "book-table",
    templateUrl: "book_table.html",
    directives: const [coreDirectives, HighlightDirective, EditableField])
class BookTable implements OnInit, AfterViewChecked {
  /**
   * Признак что включен режим редактирования произвольного поля таблицы
   */
  bool isEdit;

  @Input()
  List<Booky> bookList;

  @Input()
  List<String> headers;

  List<Booky> bookListCopy;

  @override
  ngOnInit() {
    bookListCopy = new List.from(bookList);
    isEdit = false;
  }

  editField(String value, int row, int prop) {
    print("Edit string row: $row prop: $prop");
    bookListCopy[row].setProperty(prop, value);
    bookList[row].setProperty(prop, value);
    print(bookList);
  }

  @override
  ngAfterViewChecked() {
    //  print(bookListCopy);
    // TODO: implement ngAfterViewChecked
  }
}
