<#setting url_escaping_charset="UTF-8">
      <h3>북 리스트 (Tile and FREEMARKER)</h3>
      
      <div class="bs-docs-example">
      
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>#</th>
              <th>Name</th>
              <th>Author</th>
              <th>Date</th>
              <th>Comment</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
          <#foreach book in books >
             <tr>
              <td>${book.id}</td>
              <td>${book.name}</td>
              <td>${book.author}</td>
              <td>${book.publishDate}</td>
              <td>${book.comment}</td>
              <td>${book.status}</td>
            </tr> 
          </#foreach>
          </tbody>
        </table>
      </div>