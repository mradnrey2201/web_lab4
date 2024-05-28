import {booleanAttribute, Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ConsoleLogger} from "@angular/compiler-cli";
import {Hit} from "../Module/Hit";
import {Point} from "../Module/PointModel";
const rDefault = 80
const zeroGraphic = 105
const acceptableX = [-2.0, -1.5, -1.0, -0.5, 0, 0.5, 1.0, 1.5, 2.0]

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    FormsModule,
    NgForOf
  ],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})



export class MainPageComponent implements OnInit{
  hits: Point[] = [];
   rDefault = 80
   zeroGraphic = 105

  x=0;
  y=0;
  r=0;
  hit="";
  email="";
  pswd = 0;
  cl = "";
 hited=true;
  constructor(private http:HttpClient, private  router:Router, private activatedRoute: ActivatedRoute) {
  }









  ngOnInit():void {

    // @ts-ignore

    const canvas = document.getElementById("canvas") as HTMLCanvasElement;
    canvas.addEventListener("click", this.handleCanvasClick.bind(this));


    this.activatedRoute.queryParams.subscribe(params =>{
     // console.log("зашел изменить имеил")
      this.email = params['email']
      this.pswd = params['pswd']
    })




    const hit = {x: this.x, y: this.y, r: this.r, isHit:this.hited, username: this.email}
    const ch = JSON.stringify(hit);
    this.http.post<any>("http://localhost:8080/showPoint", ch, {
      headers: {
        'Content-Type': 'application/json'
      }
    }).subscribe({
      next: ((responseHit: any) => {
        console.log(this.email)
        console.log(responseHit )
        this.hits=responseHit;
        this.hits.forEach((point: Point) => {
          let a = this.isHited(point.x,point.y,point.r);
          console.log(`x: ${point.x}, y: ${point.y}, r: ${point.r}, hit: ${point.isHit}`);
          this.draw(point.x, point.y, point.r, a);
        });


      })
    })
  }




  //canvass/////////////////////////////////////////////////////////////////////////////////////////////////
  handleCanvasClick(event: MouseEvent) {
    const canvas = event.target as HTMLCanvasElement;
    const rect = canvas.getBoundingClientRect();
    const xCoord = event.clientX - rect.left;
    const yCoord = event.clientY - rect.top;
    let x = Math.round(((xCoord - zeroGraphic) / rDefault * this.r) * 2) / 2;
    let y = parseFloat(((zeroGraphic - yCoord) / rDefault * this.r).toFixed(1));
    const r = this.r;
if(r<1){
  alert("выбирете r побольше")
}else{
    // Определение попадания
    const isHit = this.isHited(x, y, r);
  //console.log(isHit)
    // Отрисовка точки
    this.draw(x, y, r,isHit);

    // Отправка данных на сервер и обновление таблицы
    this.updateTable(x, y, r,isHit);}
  }

  draw(x: number, y: number, r: number, isHit: boolean) {
    const canvas = document.getElementById("canvas") as HTMLCanvasElement;
    const context = canvas.getContext('2d');

    if (context !== null && context !== undefined) {
      const xCoord = (x / r * rDefault) + zeroGraphic;
      const yCoord = zeroGraphic - (y / r * rDefault);

      context.beginPath();
      context.arc(xCoord, yCoord, 3, 0, 2 * Math.PI);
      context.fillStyle = isHit ? 'rgb(173, 255, 47)' : 'rgb(250, 47, 47)';
      context.strokeStyle = 'black';
      context.fill();
      context.stroke();
    }
  }

  updateTable(x: number, y: number, r: number, isHit: boolean) {
    const canvas = document.getElementById("canvas") as HTMLCanvasElement;
    const context = canvas.getContext('2d');

    if (context !== null && context !== undefined) {
      const hit = { x, y, r, isHit, username: this.email };
      const ch = JSON.stringify(hit);

      this.http.post<any>("http://localhost:8080/PointAdd", ch, {
        headers: {
          'Content-Type': 'application/json'
        }
      }).subscribe({
        next: (responseHit: Point[]) => {
          console.log(responseHit);
          this.hits = responseHit;
          this.hits.forEach((point: Point) => {
           // console.log(`x: ${point.x}, y: ${point.y}, r: ${point.r}, hit: ${point.isHit}`);
          });
        }
      });
    }
  }





  //////////////
  logout() {
    this.http.post<any>("http://localhost:8080/logout", {}).subscribe(() => {
      this.router.navigate(['/logout']);
    });
  }

  isHited(x:number, y:number, r:number):boolean{


    return ((x <= 0 && y >= 0 && (y - 2 * x <= r)) ||
      (x >= 0 && y >= 0 && y <= r && x <= r) ||
      (x >= 0 && y <= 0 && (x * x + y * y <= (r / 2) * (r / 2))))
      ;
  }



  check() {
    const r = this.r;
    if(r<1){
      alert("выбирете r побольше")
    }else {
      this.activatedRoute.queryParams.subscribe(params => {
        //console.log("зашел изменить имеил");
        this.email = params['email'];
        this.pswd = params['pswd'];
      });

      if (this.y > 5 || this.y < -5) {
        alert("У в интервале от -5 до 5");
      } else {
        this.hited = this.isHited(this.x, this.y, this.r);

        this.draw(this.x, this.y, this.r, this.hited);
        const hit = {x: this.x, y: this.y, r: this.r, isHit: this.hited, username: this.email};
        const ch = JSON.stringify(hit);

        let aHit = this.isHited(this.x, this.y, this.r);


        this.http.post<any>("http://localhost:8080/PointAdd", ch, {
          headers: {
            'Content-Type': 'application/json'
          }
        }).subscribe({
          next: ((responseHit: any) => {
            // console.log(this.email);
            console.log(responseHit);
            this.hits = responseHit;
            this.hits.forEach((point: Point) => {
              //console.log(`x: ${point.x}, y: ${point.y}, r: ${point.r}, hit: ${point.isHit !== undefined ? point.isHit : 'undefined'}`);
            });

            if (this.hits.length > 0) {
              this.hit = this.hits[this.hits.length - 1].isHit ? 'Hit' : 'Miss';
            }
          })
        });
      }
    }
  }

  protected readonly console = console;
}
