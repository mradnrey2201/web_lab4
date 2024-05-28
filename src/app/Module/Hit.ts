export class Hit{
x:bigint;
y:bigint;
r:bigint;
username:String;
isHit:boolean;


  constructor(x: bigint, y: bigint, r: bigint,isHit: boolean, username:String) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.username=username;
    this.isHit = isHit;
  }
}
