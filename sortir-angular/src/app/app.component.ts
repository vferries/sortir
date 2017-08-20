import {Component, OnInit} from '@angular/core';


declare const EventSource: any;

@Component({
  selector: 'app-root',
  template: `
    <h1>
      Welcome to {{title}}!!
    </h1>
    <ul>
      <li *ngFor="let s of someStrings">{{s}}</li>
    </ul>
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class AppComponent implements OnInit {
  title = 'app';

  someStrings: string[] = [];

  ngOnInit() {
    const source = new EventSource('http://localhost:8080/sse/send');
    source.addEventListener('message', aString => this.someStrings.push(aString.data), false);
  }
}
