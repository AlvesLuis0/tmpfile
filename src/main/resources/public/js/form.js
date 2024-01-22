(function() {

function getFile() {
  const inputElement = document.querySelector('input[type=file]')
  return inputElement.files[0]
}

async function uploadFile(file) {
  const data = new FormData()
  data.append('file', file)
  const path = '/upload'
  const request = {
    method: 'POST',
    body: data
  }
  return fetch(path, request)
    .then(response => response.json())
}

function shareLink(link) {
  const boxElement = document.getElementById('output-box')
  const linkElement = document.getElementById('output-link')
  const copyElement = document.getElementById('copy-btn')
  link = link.substring(1)
  linkElement.href = link
  const fullLink = location.href + link
  linkElement.innerText = fullLink
  boxElement.hidden = false
  copyElement.onclick = () => {
    navigator.clipboard.writeText(fullLink)
    alert('Copied!')
  }
}

const form = document.forms[0]
form.addEventListener('submit', (event) => {
  event.preventDefault()
  const file = getFile()
  const maxSize = 1 * 1024 * 1024 * 1024 // 1GB
  if(file.size > maxSize) {
    form.reset();
    alert('File too big')
    return
  }
  uploadFile(file)
    .then(json => shareLink(json.url))
  form.reset()
})

})()