import {Component, OnInit} from '@angular/core';
import {NgIf} from "@angular/common";
import {Client} from "../Module/Client";
import {HttpClient} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-start-page',
  standalone: true,
  imports: [
    NgIf,
    FormsModule
  ],
  templateUrl: './start-page.component.html',
  styleUrl: './start-page.component.css'
})


export class StartPageComponent implements OnInit{
  name = "test";
  email = "";
  pswd= "";
cl="";
  // client: Client;
  // constructor(private http: HttpClient) {
  //   this.http.get<Client>("http://localhost:8080/task").subscribe({
  //     next:((response: Client)=>{
  //       this.client=response;
  //     }),
  //     error:(error =>{
  //       console.log(error);
  //     })
  //   })
  // }







  changeName():void {
    this.name="asassas";
  }
constructor(private router: Router,private http: HttpClient) {

}
ngOnInit() {
}

  openNewPage() {
    const cl={username:this.email, password:this.pswd, }
    const body= JSON.stringify(cl);


    if (this.email === "") {
      alert("Логин не может быть пустым");
    } else if (this.pswd === "") {
      alert("Пароль должен состоять хотя бы из 1 символа");
    } else {
      this.http.post<any>("http://localhost:8080/task", body, {
        headers: {
          'Content-Type': 'application/json'
        }
      }).subscribe({
        next: ((response: any) => {
          console.log(response);
          if (response && response.cl !== null) {

            this.cl = response.cl;


            this.router.navigate(['/main'], { queryParams: { email: this.email, password: this.pswd } });
            console.log(this.email);
          } else {

            alert("Неверно введен логин или пароль");
          }
        }),
        error: (error) => {
          console.error("Error during POST request:", error);

        }
      });
    }
  }


  signin() {
    if(this.email==""){
      alert("Логин не может быть пустой")
    }else if(this.pswd==""){alert("Пароль должен состоять хотя бы из 1го символа")}
    else{
      const user={username:this.email, password:this.pswd, }
      const body= JSON.stringify(user);


      if(this.email==""){
        alert("Логин не может быть пустой")
      }else if(this.pswd==""){alert("Пароль должен состоять хотя бы из 1го символа")}
      else {
        this.http.post<any>("http://localhost:8080/add",body,{headers:{
            'Content-Type':'application/json'
          }}).subscribe({
          next: ((response: any) => {
            //console.log(response + " ===response");
            if (response && response.user !== null) {
              this.cl = response.user;
              //console.log(response.user + " this.cl");
              alert("Ваш аккаунт зарегистрирован, теперь войдите");
            } else {
              alert("Аккаунт с таким именем уже существует");
            }
          }),
          error: (error) => {
            console.error("Error during POST request:", error);

          }})

    }
  }
}}
