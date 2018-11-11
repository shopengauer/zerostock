import 'package:angular/angular.dart';
import 'package:bootjack/bootjack.dart';

// AngularDart info: https://webdev.dartlang.org/angular
// Components info: https://webdev.dartlang.org/components

@Component(
  selector: 'my-app',
  styleUrls: ['app_component.css'],
  templateUrl: 'app_component.html',
  directives: [Bootjack],
)
class AppComponent {





  // Nothing here yet. All logic is in TodoListComponent.
  AppComponent(){
    Bootjack.useDefault();
    Dropdown.use();
  }
}
