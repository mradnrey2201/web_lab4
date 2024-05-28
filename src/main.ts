import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { StartPageComponent } from './app/start-page/start-page.component';
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
