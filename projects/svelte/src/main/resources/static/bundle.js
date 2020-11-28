var app=function(){"use strict";function t(){}function n(t){return t()}function e(){return Object.create(null)}function o(t){t.forEach(n)}function r(t){return"function"==typeof t}function u(t,n){return t!=t?n==n:t!==n||t&&"object"==typeof t||"function"==typeof t}function c(t,n,e){t.insertBefore(n,e||null)}function i(t){t.parentNode.removeChild(t)}function l(t){return document.createElement(t)}function s(t){return document.createTextNode(t)}function f(){return s(" ")}function a(t,n,e,o){return t.addEventListener(n,e,o),()=>t.removeEventListener(n,e,o)}function d(t,n){t.value=null==n?"":n}let h;function p(t){h=t}const g=[],$=[],m=[],b=[],x=Promise.resolve();let y=!1;function _(t){m.push(t)}let v=!1;const k=new Set;function w(){if(!v){v=!0;do{for(let t=0;t<g.length;t+=1){const n=g[t];p(n),E(n.$$)}for(p(null),g.length=0;$.length;)$.pop()();for(let t=0;t<m.length;t+=1){const n=m[t];k.has(n)||(k.add(n),n())}m.length=0}while(g.length);for(;b.length;)b.pop()();y=!1,v=!1,k.clear()}}function E(t){if(null!==t.fragment){t.update(),o(t.before_update);const n=t.dirty;t.dirty=[-1],t.fragment&&t.fragment.p(t.ctx,n),t.after_update.forEach(_)}}const A=new Set;function C(t,n){t&&t.i&&(A.delete(t),t.i(n))}function O(t,n,e,o){if(t&&t.o){if(A.has(t))return;A.add(t),undefined.c.push((()=>{A.delete(t),o&&(e&&t.d(1),o())})),t.o(n)}}function j(t){t&&t.c()}function N(t,e,u){const{fragment:c,on_mount:i,on_destroy:l,after_update:s}=t.$$;c&&c.m(e,u),_((()=>{const e=i.map(n).filter(r);l?l.push(...e):o(e),t.$$.on_mount=[]})),s.forEach(_)}function L(t,n){const e=t.$$;null!==e.fragment&&(o(e.on_destroy),e.fragment&&e.fragment.d(n),e.on_destroy=e.fragment=null,e.ctx=[])}function S(t,n){-1===t.$$.dirty[0]&&(g.push(t),y||(y=!0,x.then(w)),t.$$.dirty.fill(0)),t.$$.dirty[n/31|0]|=1<<n%31}function T(n,r,u,c,l,s,f=[-1]){const a=h;p(n);const d=r.props||{},g=n.$$={fragment:null,ctx:null,props:s,update:t,not_equal:l,bound:e(),on_mount:[],on_destroy:[],before_update:[],after_update:[],context:new Map(a?a.$$.context:[]),callbacks:e(),dirty:f,skip_bound:!1};let $=!1;if(g.ctx=u?u(n,d,((t,e,...o)=>{const r=o.length?o[0]:e;return g.ctx&&l(g.ctx[t],g.ctx[t]=r)&&(!g.skip_bound&&g.bound[t]&&g.bound[t](r),$&&S(n,t)),e})):[],g.update(),$=!0,o(g.before_update),g.fragment=!!c&&c(g.ctx),r.target){if(r.hydrate){const t=function(t){return Array.from(t.childNodes)}(r.target);g.fragment&&g.fragment.l(t),t.forEach(i)}else g.fragment&&g.fragment.c();r.intro&&C(n.$$.fragment),N(n,r.target,r.anchor),w()}p(a)}class q{$destroy(){L(this,1),this.$destroy=t}$on(t,n){const e=this.$$.callbacks[t]||(this.$$.callbacks[t]=[]);return e.push(n),()=>{const t=e.indexOf(n);-1!==t&&e.splice(t,1)}}$set(t){var n;this.$$set&&(n=t,0!==Object.keys(n).length)&&(this.$$.skip_bound=!0,this.$$set(t),this.$$.skip_bound=!1)}}const B=[];const H=[];const M=function(){const{subscribe:n,set:e,update:o}=function(n,e=t){let o;const r=[];function c(t){if(u(n,t)&&(n=t,o)){const t=!B.length;for(let t=0;t<r.length;t+=1){const e=r[t];e[1](),B.push(e,n)}if(t){for(let t=0;t<B.length;t+=2)B[t][0](B[t+1]);B.length=0}}}return{set:c,update:function(t){c(t(n))},subscribe:function(u,i=t){const l=[u,i];return r.push(l),1===r.length&&(o=e(c)||t),u(n),()=>{const t=r.indexOf(l);-1!==t&&r.splice(t,1),0===r.length&&(o(),o=null)}}}}(H);return{subscribe:n,add:t=>o((n=>n.concat(t))),reset:e(H)}}();function P(n){let e,r,u,s,h;return{c(){var t,n,o;e=l("input"),r=f(),u=l("button"),u.textContent="Add",t=e,n="type",null==(o="text")?t.removeAttribute(n):t.getAttribute(n)!==o&&t.setAttribute(n,o)},m(t,o){c(t,e,o),d(e,n[0]),c(t,r,o),c(t,u,o),s||(h=[a(e,"input",n[2]),a(u,"click",n[1])],s=!0)},p(t,[n]){1&n&&e.value!==t[0]&&d(e,t[0])},i:t,o:t,d(t){t&&i(e),t&&i(r),t&&i(u),s=!1,o(h)}}}function z(t,n,e){let o="";return[o,function(){M.add(o),e(0,o="")},function(){o=this.value,e(0,o)}]}class D extends q{constructor(t){super(),T(this,t,z,P,u,{})}}function F(t,n,e){const o=t.slice();return o[1]=n[e],o[3]=e,o}function G(t){let n,e,o=t[1]+"";return{c(){n=l("li"),e=s(o)},m(t,o){c(t,n,o),function(t,n){t.appendChild(n)}(n,e)},p(t,n){1&n&&o!==(o=t[1]+"")&&function(t,n){n=""+n,t.wholeText!==n&&(t.data=n)}(e,o)},d(t){t&&i(n)}}}function I(n){let e,o=n[0],r=[];for(let t=0;t<o.length;t+=1)r[t]=G(F(n,o,t));return{c(){e=l("ul");for(let t=0;t<r.length;t+=1)r[t].c()},m(t,n){c(t,e,n);for(let t=0;t<r.length;t+=1)r[t].m(e,null)},p(t,[n]){if(1&n){let u;for(o=t[0],u=0;u<o.length;u+=1){const c=F(t,o,u);r[u]?r[u].p(c,n):(r[u]=G(c),r[u].c(),r[u].m(e,null))}for(;u<r.length;u+=1)r[u].d(1);r.length=o.length}},i:t,o:t,d(t){t&&i(e),function(t,n){for(let e=0;e<t.length;e+=1)t[e]&&t[e].d(n)}(r,t)}}}function J(t,n,e){let o=[];return M.subscribe((t=>{e(0,o=t)})),[o]}class K extends q{constructor(t){super(),T(this,t,J,I,u,{})}}function Q(n){let e,o,r,u,s,a;return r=new D({}),s=new K({}),{c(){e=l("h1"),e.textContent="Hello svelte !",o=f(),j(r.$$.fragment),u=f(),j(s.$$.fragment)},m(t,n){c(t,e,n),c(t,o,n),N(r,t,n),c(t,u,n),N(s,t,n),a=!0},p:t,i(t){a||(C(r.$$.fragment,t),C(s.$$.fragment,t),a=!0)},o(t){O(r.$$.fragment,t),O(s.$$.fragment,t),a=!1},d(t){t&&i(e),t&&i(o),L(r,t),t&&i(u),L(s,t)}}}return new class extends q{constructor(t){super(),T(this,t,null,Q,u,{})}}({target:document.body})}();
//# sourceMappingURL=bundle.js.map