import 'package:angular/angular.dart';
import 'package:zeroclient/component/stock_navbar.dart';

@Component(
  selector: "stock-main-page",
  templateUrl: "stock_main_page.html",
  directives: const [coreDirectives, StockNavBar],
)
class StockMainPage {


}