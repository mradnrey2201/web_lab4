import { Routes } from '@angular/router';
import {StartPageComponent} from "./start-page/start-page.component";
import {MainPageComponent} from "./main-page/main-page.component";


export const routes: Routes = [
  {path: '', component:StartPageComponent},
  {path: 'main', component: MainPageComponent},
  {path: 'logout', component:StartPageComponent}
];
