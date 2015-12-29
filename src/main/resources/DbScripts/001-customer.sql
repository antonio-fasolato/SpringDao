USE [SpringDao]
GO

/****** Object:  Table [dbo].[customer]    Script Date: 29/12/2015 22:19:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[customer](
	[id] [uniqueidentifier] NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[age] [int] NOT NULL,
 CONSTRAINT [PK_customer] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[customer] ADD  CONSTRAINT [DF_customer_name]  DEFAULT ('') FOR [name]
GO

ALTER TABLE [dbo].[customer] ADD  CONSTRAINT [DF_customer_age]  DEFAULT ((0)) FOR [age]
GO

