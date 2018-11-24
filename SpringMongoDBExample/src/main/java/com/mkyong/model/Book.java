package com.mkyong.model;


import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;
@SolrDocument(solrCoreName = "book")
public class Book implements Serializable
{

		private static final long serialVersionUID = -8243145429438016231L;
		
		@Id
		@Field
		private String id;
		
		@Field
		private String title;
		
		@Field
		private String description;
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle()
		{
				return title;
		}

		public void setTitle(String title)
		{
				this.title = title;
		}

		public String getDescription()
		{
				return description;
		}

		public void setDescription(String description)
		{
				this.description = description;
		}
		
		

		@Override
		public int hashCode()
		{
				final int prime = 31;
				int result = 1;
				result = prime * result + ((description == null) ? 0 : description.hashCode());
				result = prime * result + ((id == null) ? 0 : id.hashCode());
				result = prime * result + ((title == null) ? 0 : title.hashCode());
				return result;
		}

		@Override
		public boolean equals(Object obj)
		{
				if (this == obj)
						return true;
				if (obj == null)
						return false;
				if (getClass() != obj.getClass())
						return false;
				Book other = (Book) obj;
				if (description == null)
				{
						if (other.description != null)
								return false;
				}
				else if (!description.equals(other.description))
						return false;
				if (id == null)
				{
						if (other.id != null)
								return false;
				}
				else if (!id.equals(other.id))
						return false;
				if (title == null)
				{
						if (other.title != null)
								return false;
				}
				else if (!title.equals(other.title))
						return false;
				return true;
		}

		@Override
		public String toString()
		{
				return "Book [id=" + id + ", title=" + title + ", description=" + description + "]";
		}

		
		
}
