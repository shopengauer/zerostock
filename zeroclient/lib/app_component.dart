import 'package:angular/angular.dart';
import 'package:bootjack/bootjack.dart';
import 'package:zeroclient/component/stock_main_page.dart';

@Component(
  selector: 'my-app',
  styleUrls: ['app_component.css'],
  templateUrl: 'app_component.html',
  directives: [Bootjack, StockMainPage],
)
class AppComponent {

  // Nothing here yet. All logic is in TodoListComponent.
  AppComponent(){
    Bootjack.useDefault();
    Dropdown.use();
  }
}
