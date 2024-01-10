export function useConvertCoord(event, store){
    const radiusInPixel = store.state.canvas.radiusInPixel;
    const centerX = store.state.canvas.centerX
    const centerY = store.state.canvas.centerY
    
    function convertPixelToCoordY(coord, radiusInPixel){
        return (centerY - coord) / radiusInPixel
    }
    
    function convertPixelToCoordX(coord, radiusInPixel){
        return (coord - centerX) / radiusInPixel
    }

    const x = convertPixelToCoordX(event.offsetX, radiusInPixel)
    const y = convertPixelToCoordY(event.offsetY, radiusInPixel)
    console.log(x, y)
    return {x, y}

}