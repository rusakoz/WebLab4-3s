import { watch } from "vue";
import { useStore } from "vuex";


export function useDraw(obj){
    const store = useStore()

    const radiusInPixel = store.state.canvas.radiusInPixel;
    const centerX = store.state.canvas.centerX
    const centerY = store.state.canvas.centerY
    const widthAxisY = store.state.canvas.widthAxisY
    const heightAxisX = store.state.canvas.heightAxisX

    const ctx = obj.canvas.obj.getContext('2d');
    
    draw(obj.radius.value)

    watch(()=>obj.radius.value, (val)=>{
        if(parseFloat(val) >= 0){
            draw(val)
        }else{
            draw(1)
        }
    })

    function draw(radius){

        ctx.clearRect(0, 0, 325, 325);
        ctx.fillStyle = 'black'
    
        //четверть круга
        ctx.beginPath()
        ctx.moveTo(centerX, centerY)
        ctx.arc(centerX, centerY, radiusInPixel * radius, Math.PI/2, Math.PI, false)
        ctx.fillStyle = 'blue'
        ctx.fill()
    
        //квадрат
        ctx.beginPath()
        ctx.fillRect(centerX, centerY, radiusInPixel/2 * radius, radiusInPixel * radius)
    
        //треугольник
        ctx.moveTo(centerX, centerY - radiusInPixel * radius)
        ctx.lineTo(centerX + radiusInPixel * radius, centerY)
        ctx.lineTo(centerX, centerY)
        ctx.fill()
    
        ctx.fillStyle = 'black'
        //оси
        ctx.beginPath()
        ctx.fillRect(centerX - widthAxisY/2, 25, widthAxisY, 250)
        ctx.fillRect(25, centerY - heightAxisX/2, 250, heightAxisX)
    
        // Y X
        ctx.font = "15px serif";
        ctx.fillText("Y", 155, 25);
        ctx.fillText("X", 275, 145);
    
        //верхняя стрелка
        ctx.moveTo(centerX, 27)
        ctx.lineTo(142, 35)
        ctx.moveTo(centerX, 22)
        ctx.lineTo(141, 34)
        ctx.lineTo(centerX, 27)
        ctx.fill()
    
        ctx.moveTo(centerX, 27)
        ctx.lineTo(162, 35)
        ctx.moveTo(centerX, 22)
        ctx.lineTo(163, 34)
        ctx.lineTo(centerX, 27)
        ctx.fill()
    
        //боковая стрелка
        ctx.moveTo(272, centerY)
        ctx.lineTo(264, 140)
        ctx.moveTo(276, centerY)
        ctx.lineTo(263, 139)
        ctx.lineTo(272, centerY)
        ctx.fill()
    
        ctx.moveTo(272, centerY)
        ctx.lineTo(264, 165)
        ctx.moveTo(276, centerY)
        ctx.lineTo(263, 164)
        ctx.lineTo(272, centerY)
        ctx.fill()
    
        //черточки
        ctx.fillStyle = 'black'
        ctx.fillRect(centerX - 4.5, centerY - radiusInPixel * radius, 8, 2)
        ctx.fillRect(centerX - 4.5, centerY - radiusInPixel/2 * radius, 8, 2)
        ctx.fillRect(centerX - 4.5, centerY + radiusInPixel/2 * radius, 8, 2)
        ctx.fillRect(centerX - 4.5, centerY + radiusInPixel * radius, 8, 2)
    
        ctx.fillRect(centerX - radiusInPixel * radius, centerY - 4.5, 2, 8)
        ctx.fillRect(centerX - radiusInPixel/2 * radius, centerY - 4.5, 2, 8)
        ctx.fillRect(centerX + radiusInPixel/2 * radius, centerY - 4.5, 2, 8)
        ctx.fillRect(centerX + radiusInPixel * radius, centerY - 4.5, 2, 8)
    
        ctx.font = "15px serif";
        ctx.fillText(radius.toString(), centerX + 4.5, centerY - radiusInPixel * radius);
        ctx.fillText(radius.toString(), centerX + radiusInPixel * radius, centerY - 4.5);
    }
}