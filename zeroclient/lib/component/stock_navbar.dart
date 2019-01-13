import 'dart:async';

import 'package:angular/angular.dart';
import 'package:bootjack/bootjack.dart';

@Component(
  selector: "stock-navbar",
  templateUrl: "stock_navbar.html",
  directives: const [coreDirectives],
)
class StockNavBar implements OnInit {

  final StreamController<String> _stockListRequest = new StreamController<String>();




  @override
  ngOnInit() async {
    await Dropdown.use();
  }


}
