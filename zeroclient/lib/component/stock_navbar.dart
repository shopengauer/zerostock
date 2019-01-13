import 'package:angular/angular.dart';
import 'package:bootjack/bootjack.dart';

@Component(
  selector: "stock-navbar",
  templateUrl: "stock_navbar.html",
  directives: const [coreDirectives],
)
class StockNavBar implements OnInit {
  @override
  ngOnInit() async {
    await Dropdown.use();
  }
}
