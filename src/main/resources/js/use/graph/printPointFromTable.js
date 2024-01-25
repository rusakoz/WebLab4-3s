import { useDraw } from "use/graph/draw"

export function usePrintPointFromTable(obj, x, y, r, color, store) {
    const radiusInPixel = store.state.canvas.radiusInPixel;
    const centerX = store.state.canvas.centerX
    const centerY = store.state.canvas.centerY

    useDraw({canvas: {obj: obj}, radius: { value: r } }, store)

    const localX = centerX - 2 + radiusInPixel * x
    const localY = centerY - 2 + (-radiusInPixel * y)

    const ctx = obj.getContext('2d');

    ctx.fillStyle = color
    ctx.fillRect(localX, localY, 4, 4)

}