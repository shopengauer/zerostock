import 'package:zeroclient/component/navbar/navbar.dart';
import 'package:zeroclient/component/upload_form/upload_form.component.dart';
import 'package:angular/angular.dart';

@Component(
    selector: "main-layout",
    templateUrl: "main_layout.html",
    directives: const [coreDirectives, NavBar, UploadForm])
class MainLayout {}
