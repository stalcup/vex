function match_Match(){var Jb='',Kb=0,Lb='gwt.codesvr=',Mb='gwt.hosted=',Nb='gwt.hybrid',Ob='match.Match',Pb='__gwt_marker_match.Match',Qb='<script id="',Rb='"><\/script>',Sb='SCRIPT',Tb='#',Ub='?',Vb='/',Wb=1,Xb='base',Yb='img',Zb='clear.cache.gif',$b='meta',_b='name',ac='gwt:property',bc='content',cc='=',dc='gwt:onPropertyErrorFn',ec='Bad handler "',fc='" for "gwt:onPropertyErrorFn"',gc='gwt:onLoadErrorFn',hc='" for "gwt:onLoadErrorFn"',ic='user.agent',jc='webkit',kc='safari',lc='msie',mc=10,nc=11,oc='ie10',pc=9,qc='ie9',rc=8,sc='ie8',tc='gecko',uc='gecko1_8',vc=2,wc=3,xc=4,yc='Single-script hosted mode not yet implemented. See issue ',zc='http://code.google.com/p/google-web-toolkit/issues/detail?id=2079',Ac='4E82D2BA430A929BF670E626E8212DD1',Bc=':1',Cc=':2',Dc=':3',Ec=':4',Fc=':',Gc='DOMContentLoaded',Hc=50;var k=Jb,l=Kb,m=Lb,n=Mb,o=Nb,p=Ob,q=Pb,r=Qb,s=Rb,t=Sb,u=Tb,v=Ub,w=Vb,A=Wb,B=Xb,C=Yb,D=Zb,F=$b,G=_b,H=ac,I=bc,J=cc,K=dc,L=ec,M=fc,N=gc,O=hc,P=ic,Q=jc,R=kc,S=lc,T=mc,U=nc,V=oc,W=pc,X=qc,Y=rc,Z=sc,$=tc,_=uc,ab=vc,bb=wc,cb=xc,db=yc,eb=zc,fb=Ac,gb=Bc,hb=Cc,ib=Dc,jb=Ec,kb=Fc,lb=Gc,mb=Hc;var nb=window,ob=document,pb,qb,rb=k,sb={},tb=[],ub=[],vb=[],wb=l,xb,yb;if(!nb.__gwt_stylesLoaded){nb.__gwt_stylesLoaded={}}if(!nb.__gwt_scriptsLoaded){nb.__gwt_scriptsLoaded={}}function zb(){var b=false;try{var c=nb.location.search;return (c.indexOf(m)!=-1||(c.indexOf(n)!=-1||nb.external&&nb.external.gwtOnLoad))&&c.indexOf(o)==-1}catch(a){}zb=function(){return b};return b}
function Ab(){if(pb&&qb){pb(xb,p,rb,wb)}}
function Bb(){var e,f=q,g;ob.write(r+f+s);g=ob.getElementById(f);e=g&&g.previousSibling;while(e&&e.tagName!=t){e=e.previousSibling}function h(a){var b=a.lastIndexOf(u);if(b==-1){b=a.length}var c=a.indexOf(v);if(c==-1){c=a.length}var d=a.lastIndexOf(w,Math.min(c,b));return d>=l?a.substring(l,d+A):k}
;if(e&&e.src){rb=h(e.src)}if(rb==k){var i=ob.getElementsByTagName(B);if(i.length>l){rb=i[i.length-A].href}else{rb=h(ob.location.href)}}else if(rb.match(/^\w+:\/\//)){}else{var j=ob.createElement(C);j.src=rb+D;rb=h(j.src)}if(g){g.parentNode.removeChild(g)}}
function Cb(){var b=document.getElementsByTagName(F);for(var c=l,d=b.length;c<d;++c){var e=b[c],f=e.getAttribute(G),g;if(f){if(f==H){g=e.getAttribute(I);if(g){var h,i=g.indexOf(J);if(i>=l){f=g.substring(l,i);h=g.substring(i+A)}else{f=g;h=k}sb[f]=h}}else if(f==K){g=e.getAttribute(I);if(g){try{yb=eval(g)}catch(a){alert(L+g+M)}}}else if(f==N){g=e.getAttribute(I);if(g){try{xb=eval(g)}catch(a){alert(L+g+O)}}}}}}
__gwt_isKnownPropertyValue=function(a,b){return b in tb[a]};__gwt_getMetaProperty=function(a){var b=sb[a];return b==null?null:b};function Db(a,b){var c=vb;for(var d=l,e=a.length-A;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
function Eb(a){var b=ub[a](),c=tb[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(yb){yb(a,d,b)}throw null}
ub[P]=function(){var a=navigator.userAgent.toLowerCase();var b=ob.documentMode;if(function(){return a.indexOf(Q)!=-1}())return R;if(function(){return a.indexOf(S)!=-1&&(b>=T&&b<U)}())return V;if(function(){return a.indexOf(S)!=-1&&(b>=W&&b<U)}())return X;if(function(){return a.indexOf(S)!=-1&&(b>=Y&&b<U)}())return Z;if(function(){return a.indexOf($)!=-1||b>=U}())return _;return k};tb[P]={'gecko1_8':l,'ie10':A,'ie8':ab,'ie9':bb,'safari':cb};match_Match.onScriptLoad=function(a){match_Match=null;pb=a;Ab()};if(zb()){alert(db+eb);return}Bb();Cb();try{var Fb;Db([_],fb);Db([V],fb+gb);Db([Z],fb+hb);Db([X],fb+ib);Db([R],fb+jb);Fb=vb[Eb(P)];var Gb=Fb.indexOf(kb);if(Gb!=-1){wb=Number(Fb.substring(Gb+A))}}catch(a){return}var Hb;function Ib(){if(!qb){qb=true;Ab();if(ob.removeEventListener){ob.removeEventListener(lb,Ib,false)}if(Hb){clearInterval(Hb)}}}
if(ob.addEventListener){ob.addEventListener(lb,function(){Ib()},false)}var Hb=setInterval(function(){if(/loaded|complete/.test(ob.readyState)){Ib()}},mb)}
match_Match();(function () {var $gwt_version = "2.8.2";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {$wnd.__gwtStatsEvent(a)} : null;var $strongName = '4E82D2BA430A929BF670E626E8212DD1';function m(){}
function Y(){}
function pd(){}
function ld(){}
function vd(){}
function yd(){}
function Bd(){}
function Ed(){}
function Hd(){}
function fb(){}
function ib(){}
function Eg(){}
function Ih(){}
function Uh(a,b){}
function Md(){Md=ld}
function Mf(){Ge(this)}
function df(a){this.a=a}
function ie(a){this.a=a}
function Ke(a){this.a=a}
function _e(a){this.a=a}
function Xe(a){this.b=a}
function zf(a){this.c=a}
function ci(a){this.a=a}
function ki(a){this.a=a}
function mi(a){this.a=a}
function oi(a){this.a=a}
function qi(a){this.a=a}
function si(a){this.a=a}
function ui(a){this.a=a}
function Pf(){this.a=new Mf}
function D(){D=ld;C=new m}
function V(){V=ld;U=new Y}
function bg(){bg=ld;ag=dg()}
function cb(a){bb();ab.q(a)}
function bd(a){return a.b}
function Bh(a,b,c){this.a=c}
function B(){t(this);this.o()}
function ge(){B.call(this)}
function oe(){B.call(this)}
function Kf(){B.call(this)}
function Fg(){B.call(this)}
function se(a){he.call(this,a)}
function L(){L=ld;!!(bb(),ab)}
function Hf(){Hf=ld;Gf=new Og}
function fg(){bg();return new ag}
function Pd(a){Od(a);return a.j}
function jb(a,b){return Xd(a,b)}
function Of(a,b){return Be(a.a,b)}
function hg(a,b){return a.a.get(b)}
function He(a){return a.a.c+a.b.c}
function ze(a){return !a?null:a.M()}
function re(a,b){return Sg(a),a===b}
function mf(a,b){this.a=a;this.b=b}
function Nh(a,b){this.a=a;this.b=b}
function Wh(a,b){this.a=a;this.b=b}
function Xf(a){this.a=fg();this.b=a}
function jg(a){this.a=fg();this.b=a}
function uf(){this.a=mb(dc,Ai,1,0,5,1)}
function Hh(a,b,c){bi(Eh.d,a,b,c)}
function tg(a,b){ug(a,b,a.c.b,a.c)}
function ed(){cd==null&&(cd=[])}
function T(){I!=0&&(I=0);K=-1}
function _g(){_g=ld;Yg=new m;$g=new m}
function Pg(a){if(!a){throw bd(new ge)}}
function Qg(a){if(!a){throw bd(new Fg)}}
function Hg(a){return a!=null?s(a):0}
function zb(a){return a==null?null:a}
function vb(a,b){return a!=null&&tb(a,b)}
function u(a,b){a.b=b;b!=null&&Vg(b,yi,a)}
function Od(a){if(a.j!=null){return}_d(a)}
function S(a){$wnd.clearTimeout(a)}
function Xg(a){return a.$H||(a.$H=++Wg)}
function yb(a){return typeof a==='string'}
function xb(a){return typeof a==='number'}
function wb(a){return typeof a==='boolean'}
function M(a,b,c){return a.apply(b,c);var d}
function $h(a,b,c,d,e){a.a.fillRect(b,c,d,e)}
function Vg(b,c,d){try{b[c]=d}catch(a){}}
function Mh(a){Lh();De(Kh,a)?Ce(Kh,a):a}
function td(a){this.c=a;t(this);this.o()}
function he(a){this.c=a;t(this);this.o()}
function pg(a,b,c){this.a=a;this.b=b;this.c=c}
function Bg(a,b,c){this.d=a;this.b=c;this.a=b}
function Gh(a,b,c){ai(Eh.d,c);$h(Eh.d,0,0,a,b)}
function Xh(a,b){return new Wh(b.a-a.a,b.b-a.b)}
function Jh(a){var b;b=new Ih;b.a=a;return b}
function Td(a){var b;b=Sd(a);de(a,b);return b}
function Vd(a){var b;b=Sd(a);b.i=a;b.e=1;return b}
function t(a){a.d&&a.b!==xi&&a.o();return a}
function rf(a,b){a.a[a.a.length]=b;return true}
function Z(a,b){!a&&(a=[]);a[a.length]=b;return a}
function gg(a,b){return !(a.a.get(b)===undefined)}
function Be(a,b){return yb(b)?De(a,b):!!Vf(a.a,b)}
function kb(a,b,c,d,e,f){return lb(a,b,c,d,e,0,f)}
function vg(a){Qg(a.b!=0);return wg(a,a.a.a)}
function Ge(a){a.a=new Xf(a);a.b=new jg(a);Jf(a)}
function yg(){this.a=new Eg;this.c=new Eg;xg(this)}
function dh(){if(Zg==256){Yg=$g;$g=new m;Zg=0}++Zg}
function Sg(a){if(a==null){throw bd(new oe)}return a}
function Ld(){Ld=ld;Kd=$wnd.window.document}
function ne(){ne=ld;me=mb(_b,Ai,17,256,0,1)}
function Ee(a,b,c){return yb(b)?Fe(a,b,c):Wf(a.a,b,c)}
function De(a,b){return b==null?!!Vf(a.a,null):gg(a.b,b)}
function Lf(a,b){return zb(a)===zb(b)||a!=null&&o(a,b)}
function Gg(a,b){return zb(a)===zb(b)||a!=null&&o(a,b)}
function ob(a){return Array.isArray(a)&&a.Q===pd}
function ub(a){return !Array.isArray(a)&&a.Q===pd}
function Nf(a,b){var c;c=Ee(a.a,b,a);return c==null}
function Ud(a,b){var c;c=Sd(a);de(a,c);c.e=b?8:0;return c}
function $e(a,b){Xe.call(this,a);Tg(b,a.G());this.a=b}
function qe(a,b){Ug(b,a.length);return a.charCodeAt(b)}
function Ce(a,b){return b==null?ze(Vf(a.a,null)):hg(a.b,b)}
function Ch(){Ah();return pb(jb(Nc,1),Ai,23,0,[xh,yh,zh])}
function Zd(a){if(a.C()){return null}var b=a.i;return hd[b]}
function xg(a){a.a.a=a.c;a.c.b=a.a;a.a.b=a.c.a=null;a.b=0}
function bi(a,b,c,d){a.b=d;a.a.font=c.a+' '+d+'px '+b}
function Jf(a){var b,c;c=a;b=c.$modCount|0;c.$modCount=b+1}
function Xd(a,b){var c=a.a=a.a||[];return c[b]||(c[b]=a.u(b))}
function Uf(a,b){var c;c=a.a.get(b);return c==null?new Array:c}
function P(a,b,c){var d;d=N();try{return M(a,b,c)}finally{Q(d)}}
function Fe(a,b,c){return b==null?Wf(a.a,null,c):ig(a.b,b,c)}
function Vh(){Th();return pb(jb(Qc,1),Ai,14,0,[Sh,Ph,Oh,Rh,Qh])}
function Vf(a,b){var c;return Tf(b,Uf(a,b==null?0:(c=s(b),c|0)))}
function Tg(a,b){if(a<0||a>b){throw bd(new he(Vi+a+Wi+b))}}
function Rg(a,b){if(a<0||a>=b){throw bd(new he(Vi+a+Wi+b))}}
function Ug(a,b){if(a<0||a>=b){throw bd(new se(Vi+a+Wi+b))}}
function If(a,b){if(b.$modCount!=a.$modCount){throw bd(new Kf)}}
function R(a){L();$wnd.setTimeout(function(){throw a},0)}
function bb(){bb=ld;var a,b;b=!eb();a=new ib;ab=b?new fb:a}
function Zf(a){this.e=a;this.b=this.e.a.entries();this.a=new Array}
function wh(a,b,c){uh();this.d=a;this.c=b;this.b=c;this.a=255}
function ug(a,b,c,d){var e;e=new Eg;e.c=b;e.b=c;e.a=d;d.b=c.a=e;++a.b}
function tf(a,b,c){var d;d=(Rg(b,a.a.length),a.a[b]);a.a[b]=c;return d}
function Pe(a){var b;If(a.d,a);Qg(a.b);b=a.a.I();a.b=Oe(a);return b}
function nd(a){function b(){}
;b.prototype=a||{};return new b}
function w(b){if(!('stack' in b)){try{throw b}catch(a){}}return b}
function O(b){L();return function(){return P(b,this,arguments);var a}}
function H(){if(Date.now){return Date.now()}return (new Date).getTime()}
function og(a){if(a.a.d!=a.c){return hg(a.a,a.b.value[0])}return a.b.value[1]}
function ii(a){if(a.g.b!=0){a.f=vg(a.g);a.h=a.f.a}a.e.b==0||vg(a.e)}
function ei(a){ii(a);nh(Eh.a.clientWidth,Eh.a.clientHeight);a.f=null;xg(a.b)}
function lg(a){this.d=a;this.b=this.d.a.entries();this.a=this.b.next()}
function vh(a,b,c){this.d=Ab(a*256);this.c=Ab(b*256);this.b=Ab(c*256);this.a=255}
function hi(a,b){var c;c=b;di(a,b,c.buttons>0?(Th(),Qh):(Th(),Rh))}
function Ef(a,b){var c;for(c=a.a.length-1;c>=1;c--){Ff(a,c,Mg(b,c+1))}}
function sf(a,b,c){for(;c<a.a.length;++c){if(Gg(b,a.a[c])){return c}}return -1}
function mb(a,b,c,d,e,f){var g;g=nb(e,d);e!=10&&pb(jb(a,f),b,c,e,g);return g}
function Q(a){a&&X((V(),U));--I;if(a){if(K!=-1){S(K);K=-1}}}
function Ab(a){return Math.max(Math.min(a,2147483647),-2147483648)|0}
function fi(a,b){var c,d;c=b;tg(a.e,new Mh((d=c.code,c.key.length==1,d)));ei(a)}
function wg(a,b){var c;c=b.c;b.a.b=b.b;b.b.a=b.a;b.a=b.b=null;b.c=null;--a.b;return c}
function X(a){var b,c;if(a.b){c=null;do{b=a.b;a.b=null;c=$(b,c)}while(a.b);a.b=c}}
function W(a){var b,c;if(a.a){c=null;do{b=a.a;a.a=null;c=$(b,c)}while(a.a);a.a=c}}
function sd(){var a,b,c;b=_c();a=b.r();c=b.s();if(!re(a,c)){throw bd(new ud(a,c))}}
function de(a,b){var c;if(!a){return}b.i=a;var d=Zd(b);if(!d){hd[a]=[b];return}d.O=b}
function ad(a){var b;if(vb(a,4)){return a}b=a&&a[yi];if(!b){b=new F(a);cb(b)}return b}
function md(a,b,c){var d=function(){return a.apply(d,arguments)};b.apply(d,c);return d}
function Sd(a){var b;b=new Qd;b.j='Class$'+(a?'S'+a:''+b.g);b.b=b.j;b.h=b.j;return b}
function Cf(a){var b,c,d;d=0;for(c=a.D();c.H();){b=c.I();d=d+(b!=null?s(b):0);d=d|0}return d}
function Df(a){var b,c,d;d=1;for(c=a.D();c.H();){b=c.I();d=31*d+(b!=null?s(b):0);d=d|0}return d}
function ve(a,b){var c,d;Sg(b);for(d=b.D();d.H();){c=d.I();if(!a.F(c)){return false}}return true}
function Tf(a,b){var c,d,e;for(d=0,e=b.length;d<e;++d){c=b[d];if(Lf(a,c.L())){return c}}return null}
function db(a){var b=/function(?:\s+([\w$]+))?\s*\(/;var c=b.exec(a);return c&&c[1]||'anonymous'}
function dd(){ed();var a=cd;for(var b=0;b<arguments.length;b++){a.push(arguments[b])}}
function Lh(){Lh=ld;Kh=new Mf;Fe(Kh,'ArrowLeft','Left');Fe(Kh,'ArrowRight','Right')}
function Ah(){Ah=ld;xh=new Bh('BOLD',0,'bold');yh=new Bh('ITALIC',1,'italic');zh=new Bh('PLAIN',2,'normal')}
function Qe(a){this.d=a;this.c=new lg(this.d.b);this.a=this.c;this.b=Oe(this);this.$modCount=a.$modCount}
function Qd(){this.g=Nd++;this.j=null;this.h=null;this.f=null;this.d=null;this.b=null;this.i=null;this.a=null}
function F(a){D();t(this);this.b=a;a!=null&&Vg(a,yi,this);this.c=a==null?'null':od(a);this.a=a}
function Oe(a){if(a.a.H()){return true}if(a.a!=a.c){return false}a.a=new Zf(a.d.a);return a.a.H()}
function Dh(a,b,c,d,e){var f;return !!Eh.f&&Eh.f.b==e&&(f=Eh.h,!!f&&f.a>=a&&f.b>=b&&f.a<=a+c&&f.b<=b+d)}
function pb(a,b,c,d,e){e.O=a;e.P=b;e.Q=pd;e.__elementTypeId$=c;e.__elementTypeCategory$=d;return e}
function gd(a,b){typeof window==='object'&&typeof window['$gwt']==='object'&&(window['$gwt'][a]=b)}
function _h(a,b){return new Wh(Ab(a.a.measureText(b).width),Ab($wnd.Math.round(a.b/1.3329999446868896)))}
function di(a,b,c){var d,e;d=b;e=new Wh(Ab(d.clientX),Ab(d.clientY));tg(a.g,new Nh(e,(Xh(a.h,e),c)));ei(a)}
function Ff(a,b,c){var d;d=(Rg(b,a.a.length),a.a[b]);tf(a,b,(Rg(c,a.a.length),a.a[c]));Rg(c,a.a.length);a.a[c]=d}
function ig(a,b,c){var d;d=a.a.get(b);a.a.set(b,c===undefined?null:c);if(d===undefined){++a.c;Jf(a.b)}else{++a.d}return d}
function ue(a,b){var c,d;for(d=a.D();d.H();){c=d.I();if(zb(b)===zb(c)||b!=null&&o(b,c)){return true}}return false}
function N(){var a;if(I!=0){a=H();if(a-J>2000){J=a;K=$wnd.setTimeout(T,10)}}if(I++==0){W((V(),U));return true}return false}
function le(a){var b,c;if(a>-129&&a<128){b=a+128;c=(ne(),me)[b];!c&&(c=me[b]=new ie(a));return c}return new ie(a)}
function od(a){var b;if(Array.isArray(a)&&a.Q===pd){return Pd(q(a))+'@'+(b=s(a)>>>0,b.toString(16))}return a.toString()}
function q(a){return yb(a)?gc:xb(a)?Vb:wb(a)?Tb:ub(a)?a.O:ob(a)?a.O:a.O||Array.isArray(a)&&jb(Eb,1)||Eb}
function s(a){return yb(a)?bh(a):xb(a)?Ab((Sg(a),a)):wb(a)?(Sg(a),a)?1231:1237:ub(a)?a.m():ob(a)?Xg(a):!!a&&!!a.hashCode?a.hashCode():Xg(a)}
function o(a,b){return yb(a)?re(a,b):xb(a)?(Sg(a),a===b):wb(a)?(Sg(a),a===b):ub(a)?a.k(b):ob(a)?a===b:!!a&&!!a.equals?a.equals(b):zb(a)===zb(b)}
function gi(a){a.a.height=(Ld(),$wnd.window.window).innerHeight;a.a.width=$wnd.window.window.innerWidth;ei(a)}
function qd(){$wnd.setTimeout(wi(sd));rd();Eh=new ji($wnd.window.document.getElementById('matchgame-canvas'));ei(Eh)}
function Th(){Th=ld;Sh=new Uh('UP',0);Ph=new Uh('DOWN',1);Oh=new Uh('CLICK',2);Rh=new Uh('MOVE',3);Qh=new Uh('DRAG',4)}
function _c(){switch($c){case 1:return new yd;case 4:return new Hd;case 0:return new vd;case 2:return new Bd;}return new Ed}
function tb(a,b){if(yb(a)){return !!sb[b]}else if(a.P){return !!a.P[b]}else if(xb(a)){return !!rb[b]}else if(wb(a)){return !!qb[b]}return false}
function Og(){Lg();var a,b,c;c=Kg+++Date.now();a=Ab($wnd.Math.floor(c*Ti))&16777215;b=Ab(c-a*Ui);this.a=a^1502;this.b=b^Si}
function bh(a){_g();var b,c,d;c=':'+a;d=$g[c];if(d!=null){return Ab((Sg(d),d))}d=Yg[c];b=d==null?ah(a):Ab((Sg(d),d));dh();$g[c]=b;return b}
function Mg(a,b){var c,d;Pg(b>0);if((b&-b)==b){return Ab(b*Ng(a)*4.6566128730773926E-10)}do{c=Ng(a);d=c%b}while(c-d+(b-1)<0);return Ab(d)}
function ce(a,b){var c=0;while(!b[c]||b[c]==''){c++}var d=b[c++];for(;c<b.length;c++){if(!b[c]||b[c]==''){continue}d+=a+b[c]}return d}
function eb(){if(Error.stackTraceLimit>0){$wnd.Error.stackTraceLimit=Error.stackTraceLimit=64;return true}return 'stack' in new Error}
function nb(a,b){var c=new Array(b);var d;switch(a){case 14:case 15:d=0;break;case 16:d=false;break;default:return c;}for(var e=0;e<b;++e){c[e]=d}return c}
function ud(a,b){var c;td.call(this,(c=Ci+a+') '+Di+b+Ei+Fi==null?'null':od(Ci+a+') '+Di+b+Ei+Fi),vb(Ci+a+') '+Di+b+Ei+Fi,4)?Ci+a+') '+Di+b+Ei+Fi:null,c))}
function ai(a,b){var c,d,e,f;f=b.d;e=b.c;d=b.b;c=b.a;c==255?(a.a.fillStyle='rgb('+f+', '+e+', '+d+')'):(a.a.fillStyle='rgb('+f+', '+e+', '+d+', '+c/255+')')}
function fd(b,c,d,e){ed();var f=cd;$moduleName=c;$moduleBase=d;$c=e;function g(){for(var a=0;a<f.length;a++){f[a]()}}
if(b){try{wi(g)()}catch(a){b(c,a)}}else{wi(g)()}}
function te(a,b){var c,d,e,f,g,h;Sg(b);c=false;for(e=(h=new Qe((new Ke((new _e(b.a)).a)).a),new df(h));e.a.b;){d=(f=Pe(e.a),f.L());c=c|(g=Ee(a.a,d,a),g==null)}return c}
function mh(){var a,b,c,d,e,f;if(He(jh.a)!=2){return}e=(f=new Qe((new Ke((new _e(jh.a)).a)).a),new df(f));a=(d=Pe(e.a),d.L());b=(c=Pe(e.a),c.L());if(kh[a.a][a.b]===kh[b.a][b.b]){te(hh,jh);Ge(jh.a)}}
function lb(a,b,c,d,e,f,g){var h,i,j,k,l;k=e[f];j=f==g-1;h=j?d:0;l=nb(h,k);d!=10&&pb(jb(a,g-f),b[f],c[f],h,l);if(!j){++f;for(i=0;i<k;++i){l[i]=lb(a,b,c,d,e,f,g)}}return l}
function Lg(){Lg=ld;var a,b,c,d;Ig=mb(Bb,Ai,45,25,15,1);Jg=mb(Bb,Ai,45,33,15,1);d=1.52587890625E-5;for(b=32;b>=0;b--){Jg[b]=d;d*=0.5}c=1;for(a=24;a>=0;a--){Ig[a]=c;c*=0.5}}
function dg(){function b(){try{return (new Map).entries().next().done}catch(a){return false}}
if(typeof Map==='function'&&Map.prototype.entries&&b()){return Map}else{return eg()}}
function $(b,c){var d,e,f,g;for(e=0,f=b.length;e<f;e++){g=b[e];try{g[1]?g[0].R()&&(c=Z(c,g)):g[0].R()}catch(a){a=ad(a);if(vb(a,4)){d=a;L();R(vb(d,20)?d.p():d)}else throw bd(a)}}return c}
function jd(){hd={};!Array.isArray&&(Array.isArray=function(a){return Object.prototype.toString.call(a)==='[object Array]'});function b(){return (new Date).getTime()}
!Date.now&&(Date.now=b)}
function xe(a,b){var c,d,e;c=b.L();e=b.M();d=yb(c)?Ce(a,c):ze(Vf(a.a,c));if(!(zb(e)===zb(d)||e!=null&&o(e,d))){return false}if(d==null&&!(yb(c)?De(a,c):!!Vf(a.a,c))){return false}return true}
function oh(){var a,b,c,d,e;d=new uf;for(b=0;b<gh*gh;b++){rf(d,le(1+(b/2|0)))}Ef(d,(Hf(),Gf));for(a=0;a<gh;a++){for(c=0;c<gh;c++){kh[a][c]=(e=(Rg(0,d.a.length),d.a[0]),d.a.splice(0,1),e).a}}}
function lh(){lh=ld;ih=(uh(),rh);kh=kb(Cb,[Ai,Ai],[651,45],15,[gh,gh],2);hh=new Pf;jh=new Pf;oh();ih=new wh(192+Ab($wnd.Math.random()*64),192+Ab($wnd.Math.random()*64),192+Ab($wnd.Math.random()*64))}
function Ng(a){var b,c,d,e,f,g;e=a.a*Si+a.b*1502;g=a.b*Si+11;b=$wnd.Math.floor(g*Ti);e+=b;g-=b*Ui;e%=Ui;a.a=e;a.b=g;d=a.a*128;f=$wnd.Math.floor(a.b*Jg[31]);c=d+f;c>=2147483648&&(c-=4294967296);return c}
function Wf(a,b,c){var d,e,f,g,h;h=b==null?0:(g=s(b),g|0);e=(d=a.a.get(h),d==null?new Array:d);if(e.length==0){a.a.set(h,e)}else{f=Tf(b,e);if(f){return f.N(c)}}e[e.length]=new mf(b,c);++a.c;Jf(a.b);return null}
function Fh(a,b,c,d,e,f,g,h){var i;if(g){ai(Eh.d,g);$h(Eh.d,a,b,c,d)}if(!!h&&(i=Eh.h,!!i&&i.a>=a&&i.b>=b&&i.a<=a+c&&i.b<=b+d)){ai(Eh.d,h);$h(Eh.d,a,b,c,d)}ai(Eh.d,f);_h(Eh.d,e);return Jh(Dh(a,b,c,d,(Th(),Ph)))}
function kd(a,b,c){var d=hd,h;var e=d[a];var f=e instanceof Array?e[0]:null;if(e&&!f){_=e}else{_=(h=b&&b.prototype,!h&&(h=hd[b]),nd(h));_.P=c;!b&&(_.Q=pd);d[a]=_}for(var g=3;g<arguments.length;++g){arguments[g].prototype=_}f&&(_.O=f)}
function _d(a){if(a.B()){var b=a.c;b.C()?(a.j='['+b.i):!b.B()?(a.j='[L'+b.w()+';'):(a.j='['+b.w());a.b=b.v()+'[]';a.h=b.A()+'[]';return}var c=a.f;var d=a.d;d=d.split('/');a.j=ce('.',[c,ce('$',d)]);a.b=ce('.',[c,ce('.',d)]);a.h=d[d.length-1]}
function uh(){uh=ld;ph=new wh(0,0,0);new wh(128,128,128);qh=new vh(Xi,Xi,Xi);new vh(Yi,Yi,Yi);new vh(Zi,Zi,Zi);new vh($i,$i,$i);new vh(0.5,0.5,0.5);rh=new vh(_i,_i,_i);new vh(aj,aj,aj);sh=new vh(bj,bj,bj);new vh(cj,cj,cj);th=new vh(dj,dj,dj);new wh(255,255,255);new wh(255,255,0)}
function ah(a){var b,c,d,e;b=0;d=a.length;e=d-4;c=0;while(c<e){b=(Ug(c+3,a.length),a.charCodeAt(c+3)+(Ug(c+2,a.length),31*(a.charCodeAt(c+2)+(Ug(c+1,a.length),31*(a.charCodeAt(c+1)+(Ug(c,a.length),31*(a.charCodeAt(c)+31*b)))))));b=b|0;c+=4}while(c<d){b=b*31+qe(a,c++)}b=b|0;return b}
function cg(){if(!Object.create||!Object.getOwnPropertyNames){return false}var a='__proto__';var b=Object.create(null);if(b[a]!==undefined){return false}var c=Object.getOwnPropertyNames(b);if(c.length!=0){return false}b[a]=42;if(b[a]!==42){return false}if(Object.getOwnPropertyNames(b).length==0){return false}return true}
function ji(a){this.b=new yg;this.e=new yg;this.g=new yg;this.h=new Wh(0,0);this.a=a;this.c=a.getContext('2d');this.d=new ci(this.c);this.a.height=(Ld(),$wnd.window.window).innerHeight;this.a.width=$wnd.window.window.innerWidth;$wnd.window.setTimeout(md(ki.prototype.t,ki,[this]),1000);Kd.addEventListener('keydown',new mi(this));$wnd.window.window.addEventListener('resize',new oi(this));a.addEventListener('mousedown',new qi(this));a.addEventListener('mouseup',new si(this));a.addEventListener('mousemove',new ui(this))}
function nh(a,b){lh();var c,d,e,f,g,h,i,j,k,l;Gh(a,b,(uh(),th));Hh('Arial',(Ah(),xh),20);k=$wnd.Math.min(a,b);l=(k-10*(gh+1))/gh|0;for(f=0;f<gh;f++){for(i=0;i<gh;i++){e='';d=new Wh(f,i);g=Of(hh,d);h=kh[f][i]==1+(gh*gh/2|0);(Of(jh,d)||g)&&(e=kh[f][i]+'');h&&(e='Reset');c=h||g?ih:rh;j=h||g?ih:sh;if(Fh(10+f*(10+l),10+i*(10+l),l,l,e,qh,c,j).a){if(fh){continue}ih=new wh(192+Ab($wnd.Math.random()*64),192+Ab($wnd.Math.random()*64),192+Ab($wnd.Math.random()*64));if(h){fh=true}else{++eh;He(jh.a)==2&&Ge(jh.a);Nf(jh,d);mh()}}}}Hh('Impact',zh,l/2|0);Fh(b<a?k:20+l,b<a?20+l:k,l*5/2|0,l,'Clicks: '+eh,ph,new wh(196,196,255),null);if(fh){Hh('Arial',xh,20);if(Fh(b<a?k:10,b<a?10:k,l,l,'Yes',ph,ih,null).a){fh=false;Ge(jh.a);Ge(hh.a);eh=0}Fh(b<a?k+10+l:10,b<a?10:k+10+l,l,l,'No',ph,ih,null).a&&(fh=false)}}
function eg(){function e(){this.obj=this.createObject()}
;e.prototype.createObject=function(a){return Object.create(null)};e.prototype.get=function(a){return this.obj[a]};e.prototype.set=function(a,b){this.obj[a]=b};e.prototype['delete']=function(a){delete this.obj[a]};e.prototype.keys=function(){return Object.getOwnPropertyNames(this.obj)};e.prototype.entries=function(){var b=this.keys();var c=this;var d=0;return {next:function(){if(d>=b.length)return {done:true};var a=b[d++];return {value:[a,c.get(a)],done:false}}}};if(!cg()){e.prototype.createObject=function(){return {}};e.prototype.get=function(a){return this.obj[':'+a]};e.prototype.set=function(a,b){this.obj[':'+a]=b};e.prototype['delete']=function(a){delete this.obj[':'+a]};e.prototype.keys=function(){var a=[];for(var b in this.obj){b.charCodeAt(0)==58&&a.push(b.substring(1))}return a}}return e}
function rd(){var a,b,c;b=$doc.compatMode;a=pb(jb(gc,1),Ai,2,6,[Bi]);for(c=0;c<a.length;c++){if(re(a[c],b)){return}}a.length==1&&re(Bi,a[0])&&re('BackCompat',b)?"GWT no longer supports Quirks Mode (document.compatMode=' BackCompat').<br>Make sure your application's host HTML page has a Standards Mode (document.compatMode=' CSS1Compat') doctype,<br>e.g. by using &lt;!doctype html&gt; at the start of your application's HTML page.<br><br>To continue using this unsupported rendering mode and risk layout problems, suppress this message by adding<br>the following line to your*.gwt.xml module file:<br>&nbsp;&nbsp;&lt;extend-configuration-property name=\"document.compatMode\" value=\""+b+'"/&gt;':"Your *.gwt.xml module configuration prohibits the use of the current document rendering mode (document.compatMode=' "+b+"').<br>Modify your application's host HTML page doctype, or update your custom "+"'document.compatMode' configuration property settings."}
var xi='__noinit__',yi='__java$exception',zi={3:1,4:1},Ai={3:1},Bi='CSS1Compat',Ci='Possible problem with your *.gwt.xml module file.\nThe compile time user.agent value (',Di='does not match the runtime user.agent value (',Ei=').\n',Fi='Expect more errors.',Gi={33:1},Hi='gecko1_8',Ii='webkit',Ji='safari',Ki='msie',Li='ie10',Mi='ie9',Ni='ie8',Oi='gecko',Pi='unknown',Qi={3:1,15:1},Ri={18:1},Si=15525485,Ti=5.9604644775390625E-8,Ui=16777216,Vi='Index: ',Wi=', Size: ',Xi=0.10000000149011612,Yi=0.20000000298023224,Zi=0.30000001192092896,$i=0.4000000059604645,_i=0.6000000238418579,aj=0.699999988079071,bj=0.800000011920929,cj=0.8999999761581421,dj=0.949999988079071,ej='locale',fj='default',gj='user.agent';var _,hd,cd,$c=-1;jd();kd(1,null,{},m);_.k=function n(a){return this===a};_.l=function p(){return this.O};_.m=function r(){return Xg(this)};_.equals=function(a){return this.k(a)};_.hashCode=function(){return this.m()};var qb,rb,sb;kd(36,1,{},Qd);_.u=function Rd(a){var b;b=new Qd;b.e=4;a>1?(b.c=Xd(this,a-1)):(b.c=this);return b};_.v=function Wd(){Od(this);return this.b};_.w=function Yd(){return Pd(this)};_.A=function $d(){Od(this);return this.h};_.B=function ae(){return (this.e&4)!=0};_.C=function be(){return (this.e&1)!=0};_.e=0;_.g=0;var Nd=1;var dc=Td(1);var Ub=Td(36);kd(4,1,zi);_.n=function v(a){return new Error(a)};_.o=function A(){var a,b,c;c=this.c==null?null:this.c.replace(new RegExp('\n','g'),' ');b=(a=Pd(this.O),c==null?a:a+': '+c);u(this,w(this.n(b)));cb(this)};_.b=xi;_.d=true;var hc=Td(4);kd(27,4,zi);var Yb=Td(27);kd(9,27,zi);var ec=Td(9);kd(38,9,zi);var ac=Td(38);kd(54,38,zi);var Gb=Td(54);kd(20,54,{20:1,3:1,4:1},F);_.p=function G(){return zb(this.a)===zb(C)?null:this.a};var C;var Db=Td(20);var Eb=Td(0);kd(86,1,{});var Fb=Td(86);var I=0,J=0,K=-1;kd(68,86,{},Y);var U;var Hb=Td(68);var ab;kd(101,1,{});var Lb=Td(101);kd(55,101,{},fb);_.q=function gb(a){var b={},j;var c=[];a['fnStack']=c;var d=arguments.callee.caller;while(d){var e=(bb(),d.name||(d.name=db(d.toString())));c.push(e);var f=':'+e;var g=b[f];if(g){var h,i;for(h=0,i=g.length;h<i;h++){if(g[h]===d){return}}}(g||(b[f]=[])).push(d);d=d.caller}};var Ib=Td(55);kd(102,101,{});_.q=function hb(a){};var Kb=Td(102);kd(56,102,{},ib);var Jb=Td(56);kd(25,4,zi);var Xb=Td(25);kd(7,25,zi);var Sb=Td(7);kd(47,7,zi,ud);var Mb=Td(47);kd(72,1,Gi,vd);_.r=function wd(){return Hi};_.s=function xd(){var a=navigator.userAgent.toLowerCase();var b=$doc.documentMode;if(function(){return a.indexOf(Ii)!=-1}())return Ji;if(function(){return a.indexOf(Ki)!=-1&&b>=10&&b<11}())return Li;if(function(){return a.indexOf(Ki)!=-1&&b>=9&&b<11}())return Mi;if(function(){return a.indexOf(Ki)!=-1&&b>=8&&b<11}())return Ni;if(function(){return a.indexOf(Oi)!=-1||b>=11}())return Hi;return Pi};var Nb=Td(72);kd(70,1,Gi,yd);_.r=function zd(){return Li};_.s=function Ad(){var a=navigator.userAgent.toLowerCase();var b=$doc.documentMode;if(function(){return a.indexOf(Ii)!=-1}())return Ji;if(function(){return a.indexOf(Ki)!=-1&&b>=10&&b<11}())return Li;if(function(){return a.indexOf(Ki)!=-1&&b>=9&&b<11}())return Mi;if(function(){return a.indexOf(Ki)!=-1&&b>=8&&b<11}())return Ni;if(function(){return a.indexOf(Oi)!=-1||b>=11}())return Hi;return Pi};var Ob=Td(70);kd(73,1,Gi,Bd);_.r=function Cd(){return Ni};_.s=function Dd(){var a=navigator.userAgent.toLowerCase();var b=$doc.documentMode;if(function(){return a.indexOf(Ii)!=-1}())return Ji;if(function(){return a.indexOf(Ki)!=-1&&b>=10&&b<11}())return Li;if(function(){return a.indexOf(Ki)!=-1&&b>=9&&b<11}())return Mi;if(function(){return a.indexOf(Ki)!=-1&&b>=8&&b<11}())return Ni;if(function(){return a.indexOf(Oi)!=-1||b>=11}())return Hi;return Pi};var Pb=Td(73);kd(69,1,Gi,Ed);_.r=function Fd(){return Mi};_.s=function Gd(){var a=navigator.userAgent.toLowerCase();var b=$doc.documentMode;if(function(){return a.indexOf(Ii)!=-1}())return Ji;if(function(){return a.indexOf(Ki)!=-1&&b>=10&&b<11}())return Li;if(function(){return a.indexOf(Ki)!=-1&&b>=9&&b<11}())return Mi;if(function(){return a.indexOf(Ki)!=-1&&b>=8&&b<11}())return Ni;if(function(){return a.indexOf(Oi)!=-1||b>=11}())return Hi;return Pi};var Qb=Td(69);kd(71,1,Gi,Hd);_.r=function Id(){return Ji};_.s=function Jd(){var a=navigator.userAgent.toLowerCase();var b=$doc.documentMode;if(function(){return a.indexOf(Ii)!=-1}())return Ji;if(function(){return a.indexOf(Ki)!=-1&&b>=10&&b<11}())return Li;if(function(){return a.indexOf(Ki)!=-1&&b>=9&&b<11}())return Mi;if(function(){return a.indexOf(Ki)!=-1&&b>=8&&b<11}())return Ni;if(function(){return a.indexOf(Oi)!=-1||b>=11}())return Hi;return Pi};var Rb=Td(71);var Kd;qb=Qi;var Tb=Td(96);kd(97,1,Ai);var cc=Td(97);rb=Qi;var Vb=Td(98);kd(34,1,Qi);_.k=function ee(a){return this===a};_.m=function fe(){return Xg(this)};var Wb=Td(34);kd(50,9,zi,ge);var Zb=Td(50);kd(21,9,zi,he);var $b=Td(21);kd(17,97,{3:1,15:1,17:1},ie);_.k=function je(a){return vb(a,17)&&a.a==this.a};_.m=function ke(){return this.a};_.a=0;var _b=Td(17);var me;kd(511,1,{});kd(58,38,zi,oe);_.n=function pe(a){return new TypeError(a)};var bc=Td(58);sb={3:1,48:1,15:1,2:1};var gc=Td(2);kd(57,21,zi,se);var fc=Td(57);kd(515,1,{});kd(99,1,{});_.F=function we(a){return ue(this,a)};var ic=Td(99);kd(104,1,{85:1});_.k=function ye(a){var b,c,d;if(a===this){return true}if(!vb(a,24)){return false}d=a;if(this.a.c+this.b.c!=d.a.c+d.b.c){return false}for(c=new Qe((new Ke(d)).a);c.b;){b=Pe(c);if(!xe(this,b)){return false}}return true};_.m=function Ae(){return Cf(new Ke(this))};var uc=Td(104);kd(75,104,{85:1});var lc=Td(75);kd(103,99,{32:1});_.k=function Ie(a){var b;if(a===this){return true}if(!vb(a,32)){return false}b=a;if(b.G()!=this.G()){return false}return ve(this,b)};_.m=function Je(){return Cf(this)};var wc=Td(103);kd(10,103,{32:1},Ke);_.F=function Le(a){if(vb(a,18)){return xe(this.a,a)}return false};_.D=function Me(){return new Qe(this.a)};_.G=function Ne(){return He(this.a)};var kc=Td(10);kd(11,1,{},Qe);_.I=function Se(){return Pe(this)};_.H=function Re(){return this.b};_.b=false;var jc=Td(11);kd(100,99,{26:1});_.k=function Te(a){var b,c,d,e,f;if(a===this){return true}if(!vb(a,26)){return false}f=a;if(this.G()!=f.G()){return false}e=f.D();for(c=this.D();c.H();){b=c.I();d=e.I();if(!(zb(b)===zb(d)||b!=null&&o(b,d))){return false}}return true};_.m=function Ue(){return Df(this)};_.D=function Ve(){return new Xe(this)};_.K=function We(a){return new $e(this,a)};var oc=Td(100);kd(37,1,{},Xe);_.H=function Ye(){return this.a<this.b.G()};_.I=function Ze(){Qg(this.a<this.b.G());return this.b.J(this.a++)};_.a=0;var mc=Td(37);kd(53,37,{},$e);var nc=Td(53);kd(28,103,{32:1},_e);_.F=function af(a){return Be(this.a,a)};_.D=function bf(){var a;return a=new Qe((new Ke(this.a)).a),new df(a)};_.G=function cf(){return He(this.a)};var qc=Td(28);kd(22,1,{},df);_.H=function ef(){return this.a.b};_.I=function ff(){var a;return a=Pe(this.a),a.L()};var pc=Td(22);kd(65,1,Ri);_.k=function gf(a){var b;if(!vb(a,18)){return false}b=a;return Gg(this.a,b.L())&&Gg(this.b,b.M())};_.L=function hf(){return this.a};_.M=function jf(){return this.b};_.m=function kf(){return Hg(this.a)^Hg(this.b)};_.N=function lf(a){var b;b=this.b;this.b=a;return b};var rc=Td(65);kd(66,65,Ri,mf);var sc=Td(66);kd(106,1,Ri);_.k=function nf(a){var b;if(!vb(a,18)){return false}b=a;return Gg(this.b.value[0],b.L())&&Gg(og(this),b.M())};_.m=function of(){return Hg(this.b.value[0])^Hg(og(this))};var tc=Td(106);kd(105,100,{26:1});_.J=function pf(b){var c;c=this.K(b);try{return c.I()}catch(a){a=ad(a);if(vb(a,31)){throw bd(new he("Can't get element "+b))}else throw bd(a)}};_.D=function qf(){return this.K(0)};var vc=Td(105);kd(77,100,{3:1,26:1},uf);_.F=function vf(a){return sf(this,a,0)!=-1};_.J=function wf(a){return Rg(a,this.a.length),this.a[a]};_.D=function xf(){return new zf(this)};_.G=function yf(){return this.a.length};var yc=Td(77);kd(78,1,{},zf);_.H=function Af(){return this.a<this.c.a.length};_.I=function Bf(){Qg(this.a<this.c.a.length);this.b=this.a++;return this.c.a[this.b]};_.a=0;_.b=-1;var xc=Td(78);var Gf;kd(83,9,zi,Kf);var zc=Td(83);kd(24,75,{3:1,24:1,85:1},Mf);var Ac=Td(24);kd(42,103,{3:1,32:1},Pf);_.F=function Qf(a){return Of(this,a)};_.D=function Rf(){var a;return a=new Qe((new Ke((new _e(this.a)).a)).a),new df(a)};_.G=function Sf(){return He(this.a)};var Bc=Td(42);kd(81,1,{},Xf);_.D=function Yf(){return new Zf(this)};_.c=0;var Dc=Td(81);kd(44,1,{},Zf);_.I=function _f(){return this.d=this.a[this.c++],this.d};_.H=function $f(){var a;if(this.c<this.a.length){return true}a=this.b.next();if(!a.done){this.a=a.value[1];this.c=0;return true}return false};_.c=0;_.d=null;var Cc=Td(44);var ag;kd(79,1,{},jg);_.D=function kg(){return new lg(this)};_.c=0;_.d=0;var Gc=Td(79);kd(43,1,{},lg);_.I=function ng(){return this.c=this.a,this.a=this.b.next(),new pg(this.d,this.c,this.d.d)};_.H=function mg(){return !this.a.done};var Ec=Td(43);kd(80,106,Ri,pg);_.L=function qg(){return this.b.value[0]};_.M=function rg(){return og(this)};_.N=function sg(a){return ig(this.a,this.b.value[0],a)};_.c=0;var Fc=Td(80);kd(29,105,{3:1,26:1},yg);_.K=function zg(a){var b,c;Tg(a,this.b);if(a>=this.b>>1){c=this.c;for(b=this.b;b>a;--b){c=c.b}}else{c=this.a.a;for(b=0;b<a;++b){c=c.a}}return new Bg(this,a,c)};_.G=function Ag(){return this.b};_.b=0;var Jc=Td(29);kd(74,1,{},Bg);_.H=function Cg(){return this.b!=this.d.c};_.I=function Dg(){Qg(this.b!=this.d.c);this.c=this.b;this.b=this.b.a;++this.a;return this.c.c};_.a=0;_.c=null;var Hc=Td(74);kd(30,1,{},Eg);var Ic=Td(30);kd(31,9,{3:1,4:1,31:1},Fg);var Kc=Td(31);kd(82,1,{},Og);_.a=0;_.b=0;var Ig,Jg,Kg=0;var Lc=Td(82);kd(513,1,{});kd(502,1,{});var Wg=0;var Yg,Zg=0,$g;kd(504,1,{});var eh=0,fh=false,gh=7,hh,ih,jh,kh;kd(5,1,{},vh,wh);_.a=0;_.b=0;_.c=0;_.d=0;var ph,qh,rh,sh,th;var Mc=Td(5);kd(23,34,Qi,Bh);var xh,yh,zh;var Nc=Ud(23,Ch);var Eh;kd(76,1,{},Ih);_.a=false;var Oc=Td(76);kd(40,1,{40:1},Mh);var Kh;var Pc=Td(40);kd(41,1,{41:1},Nh);var Rc=Td(41);kd(14,34,Qi,Uh);var Oh,Ph,Qh,Rh,Sh;var Qc=Ud(14,Vh);kd(13,1,{13:1},Wh);_.k=function Yh(a){var b;if(this===a)return true;if(a==null)return false;if(Sc!=q(a))return false;b=a;if(this.a!=b.a)return false;if(this.b!=b.b)return false;return true};_.m=function Zh(){var a;a=31+this.a;a=31*a+this.b;return a};_.a=0;_.b=0;var Sc=Td(13);kd(67,1,{},ci);_.b=0;var Tc=Td(67);kd(46,1,{},ji);var Zc=Td(46);kd(119,$wnd.Function,{},ki);_.t=function li(a){ei(this.a)};kd(59,1,{},mi);_.handleEvent=function ni(a){fi(this.a,a)};var Uc=Td(59);kd(60,1,{},oi);_.handleEvent=function pi(a){gi(this.a)};var Vc=Td(60);kd(61,1,{},qi);_.handleEvent=function ri(a){di(this.a,a,(Th(),Ph))};var Wc=Td(61);kd(62,1,{},si);_.handleEvent=function ti(a){di(this.a,a,(Th(),Sh))};var Xc=Td(62);kd(63,1,{},ui);_.handleEvent=function vi(a){hi(this.a,a)};var Yc=Td(63);var Cb=Vd('I');var Bb=Vd('D');var wi=(L(),O);var gwtOnLoad=gwtOnLoad=fd;dd(qd);gd('permProps',[[[ej,fj],[gj,Hi]],[[ej,fj],[gj,Li]],[[ej,fj],[gj,Ni]],[[ej,fj],[gj,Mi]],[[ej,fj],[gj,Ji]]]);if (match_Match) match_Match.onScriptLoad(gwtOnLoad);})();