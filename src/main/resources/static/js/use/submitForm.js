export function useSubmitForm(url = "", json = {}){
    const res = fetch(url, {
        method: "POST",
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(json)
      })
      return res
}