export function useFetchPost(url = "", json = {}){
    const response = fetch(url, {
        method: "POST",
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(json)
      })
      return response
}