import 'package:angular/angular.dart';
import 'package:bootjack/bootjack.dart';

@Component(
  selector: "tb-navbar",
  templateUrl: "navbar.html",
  directives: const [coreDirectives],
)
class NavBar implements OnInit {
  @override
  ngOnInit() async {
    await Dropdown.use();
  }
}
